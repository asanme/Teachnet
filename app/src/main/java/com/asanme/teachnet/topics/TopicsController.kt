package com.asanme.teachnet.topics

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.asanme.teachnet.model.ForumThread
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * Controller used to fetch the posts or topics from the database
 */
class TopicsController {
    val db = Firebase.database("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app/")
    val dbRef = db.getReference("Threads")

    fun fetchThreads(filter:String, liveData: MutableLiveData<List<ForumThread>>) {
        dbRef
            .orderByChild("threadId")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val threadListItems : List<ForumThread> = snapshot.children.map{ dataSnapshot ->
                        dataSnapshot.getValue(ForumThread::class.java)!!
                    }.filter {
                        filter.equals(it.threadTopic)
                    }

                    liveData.postValue(threadListItems)
                }

            override fun onCancelled(error: DatabaseError) {
                //Nothing
            }
        })
    }
}