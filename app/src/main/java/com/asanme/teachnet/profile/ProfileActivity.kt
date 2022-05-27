package com.asanme.teachnet.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.ProfileScreenBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ProfileScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}