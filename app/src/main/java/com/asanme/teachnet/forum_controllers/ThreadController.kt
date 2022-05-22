package com.asanme.teachnet.forum_controllers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.asanme.teachnet.model.ForumThread
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ThreadController {
    val db = Firebase.database("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app/")
    val dbRef = db.getReference("Threads").child("threadTopic")

    fun fetchThreads(filter:String, liveData: MutableLiveData<List<ForumThread>>) {
        Log.i("LOADING QUERY: ", filter)
        dbRef
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.i("DATABASE CHANGES", snapshot.value.toString())

                    val threadListItems : List<ForumThread> = snapshot.children.map{ dataSnapshot ->
                        dataSnapshot.getValue(ForumThread::class.java)!!
                    }

                    liveData.postValue(threadListItems)
                    Log.i("LIVEDATA", "${liveData}")
                }

            override fun onCancelled(error: DatabaseError) {
                //Nothing
            }
        })
    }
}