package com.asanme.teachnet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asanme.teachnet.databinding.LoginScreenBinding
import com.asanme.teachnet.databinding.SignupScreenBinding
import com.google.firebase.auth.FirebaseAuth

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: LoginScreenBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpTextBtn.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}