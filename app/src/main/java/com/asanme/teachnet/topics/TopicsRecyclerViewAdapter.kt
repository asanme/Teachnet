package com.asanme.teachnet.topics

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.CommentContainerBinding
import com.asanme.teachnet.databinding.ThreadContainerBinding
import com.asanme.teachnet.model.ForumThread
import com.asanme.teachnet.model.User
import com.asanme.teachnet.threads.PostActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class ThreadRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val threadItems = mutableListOf<ForumThread>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ThreadItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ThreadItemViewHolder).onBind(threadItems[position])
        Log.i("THREAD INFORMATION", "OPENING POSTACTIVITY")
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, PostActivity::class.java)
            intent.putExtra("threadId", holder.itemView.findViewById<TextView>(R.id.threadIdContainer).text)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return threadItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setThreads(threadItems: List<ForumThread>){
        this.threadItems.clear()
        this.threadItems.addAll(threadItems)
        notifyDataSetChanged()
    }

    inner class ThreadItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.thread_container, parent, false)
    ){
        val db = Firebase.database("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app/")
        val dbProfileRef = db.getReference("Users")
        private val binding = ThreadContainerBinding.bind(itemView)
        fun onBind(threadItem: ForumThread){
            binding.threadTitleContainer.text = threadItem.threadTitle
            binding.threadId = threadItem.threadId
            fetchProfile(threadItem.threadOwnerId, binding)
        }

        fun fetchProfile(uid : String, binding: ThreadContainerBinding) {
            Log.i("FETCHING PROFILE:: POSTFRAGMENT:: CHANGES", "LOADING PROFILE:: ")
            val query : Query =  dbProfileRef.orderByChild("uid").equalTo(uid)
            query.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val threadListItems : List<User> =snapshot.children.map{ dataSnapshot ->
                        dataSnapshot.getValue(User::class.java)!!
                    }

                    try {
                        binding.usernameOwner = threadListItems[0].username
                        Picasso.get().load(threadListItems[0].profilePictureUrl).into(binding.userIcon)
                    } catch (exception : Exception){
                        Log.e("FETCHING PROFILE COMMENT","ERROR LOADING COMMENT")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}