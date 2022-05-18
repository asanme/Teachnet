package com.asanme.teachnet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.util.Log.i
import android.widget.Toast
import com.asanme.teachnet.databinding.SignupScreenBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: SignupScreenBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SignupScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpTextBtn.setOnClickListener{
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }

        binding.signUpBtn.setOnClickListener{
            val email = binding.emailInput.text.toString()
            val username = binding.usernameInput.text.toString()
            val name = binding.nameInput.text.toString()
            val surname = binding.surnameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            //TODO Regex for Email and password
            if(email.isNotEmpty() /*&& username.isNotEmpty() && username.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() */&& password.isNotEmpty())
            {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this, LoginScreenActivity::class.java)
                        startActivity(intent)
                        Log.i("FIREBASE INFO", "User created sucessfully")
                        Toast.makeText(this, "User was created successfully!", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.i("FIREBASE INFO", "Something went wrong? ${it.exception.toString()}")
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "All fields must be filled first", Toast.LENGTH_SHORT).show()
            }
        }
    }
}