package com.asanme.teachnet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.asanme.teachnet.databinding.HomeScreenBinding
import com.asanme.teachnet.databinding.LoginScreenBinding
import com.asanme.teachnet.model.TopicItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HomeActivity : AppCompatActivity() {
    lateinit var binding : HomeScreenBinding
    lateinit var controller : FirebaseController

    val fbController = FirebaseController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.home_screen)
        binding = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.getActionBar()?.hide()

        var frag:FunctionBarFragment = FunctionBarFragment()
        supportFragmentManager.beginTransaction().add(R.id.functionBarContainer, frag).commit()
        /*val topicItems = MutableList<TopicItem>()
        topicItems[0] = TopicItem(" "," ")

        val topicAdapter = TopicRecyclerViewAdapter()
        binding.topicAdapter.adapter = topicAdapter
        topicAdapter.setTopicItems()*/
    }

    //TODO Parse Firebase Realtime document to load the images and topics
    //TODO Load database topics and images from Firebase Realtime database
}