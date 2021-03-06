package com.asanme.teachnet.topics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.ForumScreenBinding
import com.asanme.teachnet.fragments.FunctionBarFragment
import com.asanme.teachnet.threads.PostActivity

/**
 * This class represents the topic section
 */
class ForumActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ForumScreenBinding = DataBindingUtil.setContentView(this, R.layout.forum_screen)
        val threadViewModel : ThreadViewModel = ViewModelProvider(this)[ThreadViewModel::class.java]
        binding.lifecycleOwner = this
        binding.threadViewModel = threadViewModel

        val topicName = intent.getStringExtra("topicName")
        val topicId = intent.getStringExtra("topicId")

        binding.setTopicName(topicName)
        val threadAdapter = ThreadRecyclerViewAdapter()
        binding.threadContainer.adapter = threadAdapter

        threadViewModel.fetchThreads(topicName!!)
        threadViewModel.threadData.observe(this) { threadItems ->
            threadAdapter.setThreads(threadItems)
        }

        var frag = FunctionBarFragment()
        supportFragmentManager.beginTransaction().add(R.id.functionBarContainer, frag).commit()

        binding.createPostBtn.setOnClickListener {
            val int = Intent(this, PostActivity::class.java)
            int.putExtra("topicName", topicName)
            startActivity(int)
        }
    }
}