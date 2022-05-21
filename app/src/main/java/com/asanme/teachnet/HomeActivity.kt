package com.asanme.teachnet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asanme.teachnet.databinding.HomeScreenBinding
import com.asanme.teachnet.databinding.LoginScreenBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : HomeScreenBinding
    lateinit var controller : FirebaseController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.home_screen)
        binding = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.getActionBar()?.hide()

        var frag:FunctionBarFragment = FunctionBarFragment()
        supportFragmentManager.beginTransaction().add(R.id.functionBarContainer, frag).commit()
        controller = FirebaseController()
        controller.retrieveUserData("RWwbV2bJ0XNt2wjDMwQTpKqmeay1") //Test
        controller.retrieveTopics()
    }

    //TODO Parse Firebase Realtime document to load the images and topics
    //TODO Load database topics and images from Firebase Realtime database
}