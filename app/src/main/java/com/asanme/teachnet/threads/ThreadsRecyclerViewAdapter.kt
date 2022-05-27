package com.asanme.teachnet.threads

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.CommentContainerBinding
import com.asanme.teachnet.model.CommentItem
import com.asanme.teachnet.model.User
import com.asanme.teachnet.profile.ProfileActivity
import com.asanme.teachnet.profile.UserProfileActivity
import com.asanme.teachnet.topics.ForumActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

/**
 * This class is responsible for updating the topics in realtime and also adding new ones to the RecyclerView in the post_container.xml file.
 */
class PostRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val commentItems = mutableListOf<CommentItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommentItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommentItemViewHolder).onBind(commentItems[position])
        holder.itemView.findViewById<TextView>(R.id.commentUserNameContainer).setOnClickListener {
            val intent = Intent(it.context, UserProfileActivity::class.java)
            intent.putExtra("userId", holder.itemView.findViewById<TextView>(R.id.userIdContainer).text.toString())
            var idk = holder.itemView.findViewById<TextView>(R.id.userIdContainer).text.toString()
            Log.i("LASTCCCCCCC", idk)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return commentItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setThreads(threadItems: List<CommentItem>){
        this.commentItems.clear()
        this.commentItems.addAll(threadItems)
        notifyDataSetChanged()
    }

    inner class CommentItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.comment_container, parent, false)
    ){
        val db = Firebase.database("https://teachnet-asanme-default-rtdb.europe-west1.firebasedatabase.app/")
        val dbProfileRef = db.getReference("Users")
        private val binding = CommentContainerBinding.bind(itemView)
        fun onBind(threadItem: CommentItem){
            binding.commentContainer.text = threadItem.threadMessage
            binding.userId = threadItem.ownerId
            fetchProfile(threadItem.ownerId, binding)
            Log.i("USERID INFO FOR COMMENTS", threadItem.ownerId.toString())
        }

        fun fetchProfile(uid : String, binding: CommentContainerBinding) {
            Log.i("FETCHING PROFILE:: POSTFRAGMENT:: CHANGES", "LOADING PROFILE:: ")
            val query : Query =  dbProfileRef.orderByChild("uid").equalTo(uid)
            query.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val threadListItems : List<User> =snapshot.children.map{ dataSnapshot ->
                        dataSnapshot.getValue(User::class.java)!!
                    }

                    try {
                        binding.username = threadListItems[0].username
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