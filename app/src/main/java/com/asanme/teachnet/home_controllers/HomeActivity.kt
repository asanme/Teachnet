package com.asanme.teachnet.home_controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.HomeScreenBinding
import com.asanme.teachnet.fragment_controllers.FunctionBarFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : HomeScreenBinding = DataBindingUtil.setContentView(this, R.layout.home_screen)

        val topicViewModel : TopicViewModel = ViewModelProvider(this)[TopicViewModel::class.java]

        binding.lifecycleOwner = this

        binding.topicViewModel = topicViewModel

        val topicAdapter = TopicRecyclerViewAdapter()
        binding.topicContainer.adapter = topicAdapter

        topicViewModel.fetchTopics()
        topicViewModel.topicData.observe(this) { topicItems ->
            topicAdapter.setTopics(topicItems)
        }

        var frag = FunctionBarFragment()
        supportFragmentManager.beginTransaction().add(R.id.functionBarContainer, frag).commit()
    }
}