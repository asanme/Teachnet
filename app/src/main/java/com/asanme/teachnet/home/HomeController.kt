package com.asanme.teachnet.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.asanme.teachnet.model.TopicItem
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * Controller used to fetch the topics from the database
 */
class HomeController {
    val db = Firebase.database("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app/")
    val dbRef = db.getReference("Topics")

    fun fetchTopics(liveData: MutableLiveData<List<TopicItem>>) {
        dbRef
            .orderByChild("topicName")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val topicListItems : List<TopicItem> = snapshot.children.map{ dataSnapshot ->
                        dataSnapshot.getValue(TopicItem::class.java)!!
                    }

                    liveData.postValue(topicListItems)
                }

            override fun onCancelled(error: DatabaseError) {
                //Nothing
            }
        })
    }
}