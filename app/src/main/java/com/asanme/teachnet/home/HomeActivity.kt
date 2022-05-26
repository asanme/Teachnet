package com.asanme.teachnet.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.HomeScreenBinding
import com.asanme.teachnet.fragments.FunctionBarFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : HomeScreenBinding = DataBindingUtil.setContentView(this, R.layout.home_screen)
        val homeViewModel : HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        binding.lifecycleOwner = this
        binding.topicViewModel = homeViewModel

        val topicAdapter = HomeRecyclerViewAdapter()
        binding.topicContainer.adapter = topicAdapter


        homeViewModel.fetchTopics()
        homeViewModel.topicData.observe(this) { topicItems ->
            topicAdapter.setTopics(topicItems)
        }

        var frag = FunctionBarFragment()
        supportFragmentManager.beginTransaction().add(R.id.functionBarContainer, frag).commit()
    }
}