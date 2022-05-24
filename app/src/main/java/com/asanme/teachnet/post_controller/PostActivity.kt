package com.asanme.teachnet.post_controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.PostScreenBinding

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : PostScreenBinding = DataBindingUtil.setContentView(this, R.layout.post_screen)
        val postViewModel : PostViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        binding.lifecycleOwner = this
        binding.postViewModel = postViewModel

        val threadId = intent.getStringExtra("threadId")
        Log.i("THREADID:", "${threadId}")
        val threadName = intent.getStringExtra("threadName")
        Log.i("THREADNAME:", "${threadName}")

        binding.threadName = threadName

        val postAdapter = PostRecyclerViewAdapter()
        binding.threadContainer.adapter = postAdapter

        postViewModel.fetchComents(threadId!!)
        postViewModel.threadData.observe(this) { threadItems ->
            postAdapter.setThreads(threadItems)
        }

        //TODO Use the postId fetched from the last activity to load the data in case it changes (Realtime Database) (OPTIONAL)
    }
}