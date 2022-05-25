package com.asanme.teachnet.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.asanme.teachnet.databinding.WelcomeScreenBinding
import com.asanme.teachnet.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : AppCompatActivity() {

    lateinit var firebaseAuth : FirebaseAuth
    lateinit var binding: WelcomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.signInBtn.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
        binding.signUpBtn.setOnClickListener { startActivity(Intent(this, SignUpActivity::class.java)) }
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.currentUser?.let{
            Log.i("CURRENTUSER::: ", firebaseAuth.currentUser.toString())
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}