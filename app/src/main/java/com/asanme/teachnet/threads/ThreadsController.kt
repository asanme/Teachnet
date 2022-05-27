package com.asanme.teachnet.threads

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.asanme.teachnet.model.CommentItem
import com.asanme.teachnet.model.ForumThread
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/**
 * Controller used to fetch the posts or threads from the database
 */
class PostController {
    val db = Firebase.database("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app/")
    val dbRef = db.getReference("Comments")

    fun fetchComments(filter:String, liveData: MutableLiveData<List<CommentItem>>) {
        Log.i("LOADING QUERY: ", filter)
        dbRef
            .orderByChild("threadId")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.i("DATABASE CHANGES", snapshot.value.toString())

                    val threadListItems : List<CommentItem> = snapshot.children.map{ dataSnapshot ->
                        dataSnapshot.getValue(CommentItem::class.java)!!
                    }.filter {
                        filter.equals(it.threadId)
                    }

                    Log.i("DATABASE CHANGES", threadListItems.toString())
                    liveData.postValue(threadListItems)
                    Log.i("LIVEDATA", "${liveData}")
                }

            override fun onCancelled(error: DatabaseError) {
                //Nothing
            }
        })
    }

    fun fetchPost(filter:String, post: MutableLiveData<List<ForumThread>>) {
        Log.i("LOADING QUERY: ", filter)
        dbRef
            .orderByChild("threadId")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.i("DATABASE CHANGES", snapshot.value.toString())

                    val threadListItems : List<ForumThread> = snapshot.children.map{ dataSnapshot ->
                        dataSnapshot.getValue(ForumThread::class.java)!!
                    }.filter {
                        filter.equals(it.threadId)
                    }

                    Log.i("DATABASE CHANGES", threadListItems.toString())
                    post.postValue(threadListItems)
                    Log.i("LIVEDATA", "${post}")
                }

                override fun onCancelled(error: DatabaseError) {
                    //Nothing
                }
            })
    }

}