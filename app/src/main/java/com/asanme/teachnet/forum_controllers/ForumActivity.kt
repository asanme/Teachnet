package com.asanme.teachnet.forum_controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.ForumScreenBinding
import com.asanme.teachnet.databinding.HomeScreenBinding
import com.asanme.teachnet.fragment_controllers.FunctionBarFragment
import com.asanme.teachnet.home_controllers.TopicViewModel

class ForumActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ForumScreenBinding = DataBindingUtil.setContentView(this, R.layout.forum_screen)
        binding.lifecycleOwner = this

        Log.i("TOPICID:", "${intent.getStringExtra("topicId")}")
        binding.setTopicName(intent.getStringExtra("topicName"))

        //TODO Use the postId fetched from the last activity to load the data in case it changes (Realtime Database)

        var frag = FunctionBarFragment()
        supportFragmentManager.beginTransaction().add(R.id.functionBarContainer, frag).commit()
    }
}