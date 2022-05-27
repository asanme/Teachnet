package com.asanme.teachnet.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.asanme.teachnet.databinding.WelcomeScreenBinding
import com.asanme.teachnet.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth

/**
 * This class checks if the user is still logged in from the last session, in case he isn't authenticated,
 * the user will have to create a new account or log in
 */
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

    /**
     * When the app starts, check if user is logged in
     */
    override fun onStart() {
        super.onStart()
        firebaseAuth.currentUser?.let{
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}