package com.asanme.teachnet

import android.util.Log
import com.asanme.teachnet.model.TopicItem
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseController {
    val db = Firebase.database("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app/")
    val dbRef = db.getReference("Topics")

    init {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.i("DATABASE CHANGES", snapshot.value.toString())

                val listTopics : List<TopicItem> = snapshot.children.map{ dataSnapshot ->
                    dataSnapshot.getValue(TopicItem::class.java)!!
                }

                Log.i("ITEMS", listTopics.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                //Nothing
            }
        })
    }
}