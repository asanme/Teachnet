package com.asanme.teachnet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asanme.teachnet.databinding.LoginScreenBinding
import com.asanme.teachnet.databinding.WelcomeScreenBinding

class WelcomeActivity : AppCompatActivity() {
    lateinit var binding: WelcomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.signInBtn.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
        binding.signUpBtn.setOnClickListener { startActivity(Intent(this, SignUpActivity::class.java)) }
    }
}