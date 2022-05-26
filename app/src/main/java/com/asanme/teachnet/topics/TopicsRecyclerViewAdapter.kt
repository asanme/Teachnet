package com.asanme.teachnet.topics

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.ThreadContainerBinding
import com.asanme.teachnet.model.ForumThread
import com.asanme.teachnet.threads.PostActivity
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
            Log.i("THREAD INFORMATION", "OPENING POSTACTIVITY")

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
        private val binding = ThreadContainerBinding.bind(itemView)
        fun onBind(threadItem: ForumThread){
            binding.threadTitleContainer.text = threadItem.threadTitle
            binding.userNameContainer.text = threadItem.threadOwnerId
            binding.threadId = threadItem.threadId
            Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png").into(binding.userIcon)
        }
    }
}