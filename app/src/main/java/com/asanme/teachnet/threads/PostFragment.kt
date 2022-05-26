package com.asanme.teachnet.threads

import android.content.Context
import android.os.Bundle
import android.text.style.UpdateLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.PostContainerBinding
import com.asanme.teachnet.databinding.PostScreenBinding
import com.asanme.teachnet.model.ForumThread
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class PostFragment: Fragment() {
    lateinit var v: View
    lateinit var firebaseAuth : FirebaseAuth
    lateinit var binding : PostContainerBinding
    val db = Firebase.database("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app/")
    val dbRef = db.getReference("Threads")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.post_container, container, false)
        binding.lifecycleOwner = this
        firebaseAuth = FirebaseAuth.getInstance()
        v = binding.root


        arguments?.getString("threadId")?.let { threadId ->
            Log.i("POSTFRAGMENT:: ATTACHED POSTFRAGMENT", "THREAD ID IS ${threadId}")
            activity?.let{
                fetchPost(threadId)
            } ?: run {
                Log.i("POSTFRAGMENT:: THE ACTIVITY IS NULL", "SHEEEEEEEEEIT")
            }

        } //If we DONT recieve an argument that means we are creating a post
            ?: run {
                Log.i("POSTFRAGMENT:: CREATING NEW POST SO NULL!", "SHEEEEEEEEEIT")
            }

        val uid = firebaseAuth.uid
        uid?.let {
            var pushBtn = v.findViewById<ImageView>(R.id.pushBtn)
            pushBtn.setOnClickListener {
                Toast.makeText(context, "The UID is ${uid}", Toast.LENGTH_SHORT).show()
            }
        }

        return v
    }

    fun fetchPost(threadId:String) {
        val query : Query =  dbRef.orderByChild("threadId").equalTo(threadId)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val threadListItems : List<ForumThread> =snapshot.children.map{ dataSnapshot ->
                    dataSnapshot.getValue(ForumThread::class.java)!!
                }

                Log.i("POSTFRAGMENT:: CHANGES ", "SHEEEEEEEEEEEEESH ${threadListItems[0].threadMessage}" )
                binding.threadTitle = threadListItems[0].threadTitle
                binding.threadMessage =  threadListItems[0].threadMessage
                //binding.userId =  threadListItems[0].threadOwnerId
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun isEditable(): Boolean {
        var isOwner : Boolean = false
        return isOwner
    }
}