package com.asanme.teachnet.profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.asanme.teachnet.R
import com.asanme.teachnet.auth.WelcomeActivity
import com.asanme.teachnet.databinding.PostScreenBinding
import com.asanme.teachnet.databinding.ProfileScreenBinding
import com.asanme.teachnet.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

/**
 * This class represents the Profile view, which loads the information based on the current user logged in,
 * thanks to FirebaseAuth, which can return an item containing the current ID of the user
 */
class ProfileActivity : AppCompatActivity() {
    val db = Firebase.database("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app/")
    val dbProfileRef = db.getReference("Users")
    lateinit var firebaseAuth : FirebaseAuth

    private lateinit var binding: ProfileScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ProfileScreenBinding = DataBindingUtil.setContentView(this, R.layout.profile_screen)
        binding.lifecycleOwner = this

        firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUid = firebaseAuth.uid.toString()

        fetchProfile(firebaseUid, binding,this)
        binding.logoutBtn.setOnClickListener {
            Toast.makeText(this, "Clicked?", Toast.LENGTH_SHORT).show()
            firebaseAuth.signOut()
            startActivity(Intent(this, WelcomeActivity::class.java))
        }
    }

    fun fetchProfile(uid : String, binding:ProfileScreenBinding, context: Context) {
        val query : Query =  dbProfileRef.orderByChild("uid").equalTo(uid)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val threadListItems : List<User> =snapshot.children.map{ dataSnapshot ->
                    dataSnapshot.getValue(User::class.java)!!
                }

                try {
                    binding.username = threadListItems[0].username
                    binding.name = threadListItems[0].name
                    binding.surname = threadListItems[0].surname
                    Picasso.get().load(threadListItems[0].profilePictureUrl).into(binding.iconContainer)
                } catch (exception : Exception){
                    Toast.makeText(context, "An error occurred while fetching the profile", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}