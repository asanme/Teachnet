package com.asanme.teachnet.threads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.PostScreenBinding
import com.asanme.teachnet.fragments.FunctionBarFragment


/**
 * This activity represents the main activity for the Thread screen.
 * This class also controls the view, by adding a RecyclerView and fetching the items from Firebase Realtime Database
 */
class PostActivity : AppCompatActivity() {
    lateinit var threadId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : PostScreenBinding = DataBindingUtil.setContentView(this, R.layout.post_screen)
        val threadsViewModel : ThreadsViewModel = ViewModelProvider(this)[ThreadsViewModel::class.java]
        binding.lifecycleOwner = this
        binding.postViewModel = threadsViewModel

        val threadId = intent.getStringExtra("threadId")
        val topicName = intent.getStringExtra("topicName")

        threadId?.let {
            val postAdapter = PostRecyclerViewAdapter()
            binding.threadContainer.adapter = postAdapter

            threadsViewModel.fetchComents(threadId)
            threadsViewModel.threadData.observe(this) { threadItems ->
                postAdapter.setThreads(threadItems)
            }
        }

        val bundle = Bundle()
        bundle.putString("threadId", threadId)
        bundle.putString("topicName", topicName)
        var frag = PostFragment()
        frag.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fragContainer, frag).commit()

        //TODO Use the postId fetched from the last activity to load the data in case it changes (Realtime Database) (OPTIONAL)
    }
}