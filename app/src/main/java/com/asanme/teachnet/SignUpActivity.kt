package com.asanme.teachnet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.asanme.teachnet.databinding.SignupScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


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

        //Go to login
        binding.signUpTextBtn.setOnClickListener{ startActivity(Intent(this, LoginActivity::class.java)) }

        //Register user
        binding.signUpBtn.setOnClickListener{
            Log.i("FIREBASE INFO", "Connecting to db")
            val email = binding.emailInput.text.toString()
            val username = binding.usernameInput.text.toString()
            val name = binding.nameInput.text.toString()
            val surname = binding.surnameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            //TODO Check AuthResult (it) and display error depending on the exception
            //TODO Prevent server from creating the user (FirebaseAuth) in case there's a problem with Realtime Database
            // Possible solution: Checking if the entered values are valid before creating with Auth
            if(email.isNotEmpty() && username.isNotEmpty() && username.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() && password.isNotEmpty())
            {
                Log.i("FIREBASE INFO", "Not empty")

                //User to insert into
                val user = User(email,username,name,surname)
                connection.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        FirebaseDatabase
                            .getInstance("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app")
                            .getReference("Users")
                            .child(connection.currentUser!!.uid)
                            .setValue(user)
                            .addOnCompleteListener {
                                if(it.isSuccessful)
                                {
                                    startActivity(Intent(this, HomeActivity::class.java))
                                    Log.i("FIREBASE INFO", "Registered sucessfully")
                                    Toast.makeText(this, "User was created successfully!", Toast.LENGTH_SHORT).show()
                                }
                                else
                                {
                                    Log.i("FIREBASE INFO", "Something went wrong ${it.exception.toString()}")
                                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                                }
                            }
                    }
                    else
                    {
                        Log.i("FIREBASE INFO", "Something went wrong ${it.exception.toString()}")
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
            else
            {
                Toast.makeText(this, "All fields must be filled first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Uses Firebase Auth to create the user, then we add additional data to Firebase Realtime Database (username, name, surname)
     */
    private fun registerUser(connection: FirebaseAuth){

    }
}