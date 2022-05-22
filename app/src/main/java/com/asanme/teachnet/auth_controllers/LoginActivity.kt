package com.asanme.teachnet.auth_controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.asanme.teachnet.home_controllers.HomeActivity
import com.asanme.teachnet.databinding.LoginScreenBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginScreenBinding
    private lateinit var connection: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connection = FirebaseAuth.getInstance()

        binding.signUpTextBtn.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.signInBtn.setOnClickListener{
            val email = binding.emailInput.text.toString()
            val pswd = binding.passwordInput.text.toString()
            if(email.isNotEmpty() && pswd.isNotEmpty()){
                connection.signInWithEmailAndPassword(email, pswd).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                    } else {
                        Toast.makeText(this, "Failed to log in ${it.exception.toString()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}