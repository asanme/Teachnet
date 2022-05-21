package com.asanme.teachnet

import android.util.Log
import com.google.firebase.database.FirebaseDatabase

class FirebaseController {
    fun retrieveUserData(uid:String){
        FirebaseDatabase
            .getInstance("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("Users")
            .child(uid)
            .get()
            .addOnSuccessListener {
            Log.i("FIREBASECONTROLLER", "Got value ${it.value}")
        }.addOnFailureListener{
            Log.e("FIREBASECONTROLLER", "Error getting data", it)
        }
    }

    fun retrieveTopics(){

        val inst = FirebaseDatabase
            .getInstance("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("Topics")
            .get()
            .addOnCompleteListener {
                Log.i("FIREBASECONTROLLERINFO", "Returned ${it.result}")
            }

    }
}