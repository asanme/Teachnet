package com.asanme.teachnet.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.asanme.teachnet.home.HomeActivity
import com.asanme.teachnet.databinding.SignupScreenBinding
import com.asanme.teachnet.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

/**
 * This class uses FirebaseAuth and FirebaseDatabase to create a user and add it to the Firebase Realtime Database
 */
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: SignupScreenBinding
    private lateinit var connection: FirebaseAuth
    private lateinit var db: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SignupScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connection = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()

        binding.signUpTextBtn.setOnClickListener{ startActivity(Intent(this, LoginActivity::class.java)) }

        binding.signUpBtn.setOnClickListener{
            val email = binding.emailInput.text.toString()
            val username = binding.usernameInput.text.toString()
            val name = binding.nameInput.text.toString()
            val surname = binding.surnameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if(email.isNotEmpty() && username.isNotEmpty() && username.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() && password.isNotEmpty() && password.length >= 6)
            {
                val user = User(email,username,name,surname)
                connection.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        val newUser = User(email, name, "https://www.seekpng.com/png/detail/966-9665493_my-profile-icon-blank-profile-image-circle.png", surname, connection.uid.toString(), username)
                        FirebaseDatabase
                            .getInstance("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app")
                            .getReference("Users")
                            .push()
                            .setValue(newUser)
                            .addOnCompleteListener {
                                if(it.isSuccessful)
                                {
                                    startActivity(Intent(this, HomeActivity::class.java))
                                    Toast.makeText(this, "User was created successfully!", Toast.LENGTH_SHORT).show()
                                }
                                else
                                {
                                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                                }
                            }
                    }
                    else
                    {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
            else
            {
                Toast.makeText(this, "All fields must be filled first and password must be greater than 5 characters", Toast.LENGTH_SHORT).show()
            }
        }
    }
}