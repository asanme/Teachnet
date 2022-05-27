package com.asanme.teachnet.threads

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.PostContainerBinding
import com.asanme.teachnet.model.ForumThread
import com.asanme.teachnet.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class PostFragment: Fragment() {
    lateinit var v: View
    lateinit var firebaseAuth : FirebaseAuth
    lateinit var binding : PostContainerBinding
    val db = Firebase.database("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app/")
    val dbThreadRef = db.getReference("Threads")
    val dbProfileRef = db.getReference("Users")

    var isOwner = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.post_container, container, false)

        binding.lifecycleOwner = this
        firebaseAuth = FirebaseAuth.getInstance()


        v = binding.root
        val uid = firebaseAuth.uid
        uid?.let {
            arguments?.getString("threadId")?.let { threadId ->
                Log.i("POSTFRAGMENT:: ATTACHED POSTFRAGMENT", "THREAD ID IS ${threadId}")
                activity?.let{
                    fetchPost(threadId, uid)

                    binding.pushBtn.setOnClickListener {
                        Toast.makeText(context, "You are the owner of this post!", Toast.LENGTH_SHORT).show()
                    }
                } ?: run {
                    Log.i("POSTFRAGMENT:: THE ACTIVITY IS NULL", "SHEEEEEEEEEIT")
                }

            } ?: run {
                    Log.i("POSTFRAGMENT:: CREATING NEW POST SO NULL!", "SHEEEEEEEEEIT")
                    fetchProfile(uid)
                    binding.pushBtn.setOnClickListener {
                        arguments?.getString("topicName")?.let { topicName ->
                            if(binding.threadTitleContainer.text.toString() != "" && binding.threadBody.text.toString() != ""){
                                Toast.makeText(context, "You created a new post!", Toast.LENGTH_SHORT).show()
                                val key = dbThreadRef.push().key ?: ""
                                val postInfo = ForumThread(key, uid, binding.threadTitleContainer.text.toString(), binding.threadBody.text.toString(), topicName)
                                addPost(postInfo)
                            } else {
                                Toast.makeText(context, "Sugondeez!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
        } ?: run{
            Toast.makeText(context, "You are not logged in!", Toast.LENGTH_SHORT).show()
        }

        return v
    }

    fun fetchPost(threadId:String, currentUserId : String) {
        val query : Query =  dbThreadRef.orderByChild("threadId").equalTo(threadId)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val threadListItems : List<ForumThread> =snapshot.children.map{ dataSnapshot ->
                    dataSnapshot.getValue(ForumThread::class.java)!!
                }

                Log.i("POSTFRAGMENT:: CHANGES ", "SHEEEEEEEEEEEEESH ${threadListItems[0].threadOwnerId} , $currentUserId ")
                binding.threadTitle = threadListItems[0].threadTitle
                binding.threadMessage =  threadListItems[0].threadMessage


                fetchProfile(threadListItems[0].threadOwnerId)
                if(!isOwner(threadListItems[0].threadOwnerId.toString(), currentUserId)){
                    binding.pushBtn.isEnabled = false
                    binding.pushBtn.isInvisible = true
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun fetchProfile(uid : String) {
        Log.i("FETCHING PROFILE:: POSTFRAGMENT:: CHANGES", "LOADING PROFILE:: ")
        val query : Query =  dbProfileRef.orderByChild("uid").equalTo(uid)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val threadListItems : List<User> =snapshot.children.map{ dataSnapshot ->
                    dataSnapshot.getValue(User::class.java)!!
                }

                try {
                    binding.username = threadListItems[0].username
                    Picasso.get().load(threadListItems[0].profilePictureUrl).into(binding.creatorIcon)
                } catch (exception : Exception){
                    Toast.makeText(context, "An error occurred while fetching the profile", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


    fun addPost(postInfo:ForumThread){
        dbThreadRef.push().setValue(postInfo).addOnCompleteListener {
            Toast.makeText(context, "Created post sucessfully!", Toast.LENGTH_SHORT).show()
        }
    }

    fun isOwner(ownerId:String, currentUserId: String): Boolean {
        if(ownerId.equals(currentUserId)){
            return true
        }

        return false
    }
}