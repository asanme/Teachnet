package com.asanme.teachnet.threads

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.CommentContainerBinding
import com.asanme.teachnet.model.CommentItem

class PostRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val commentItems = mutableListOf<CommentItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommentItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommentItemViewHolder).onBind(commentItems[position])
        /*holder.itemView.setOnClickListener {
            //TODO Go to the desired thread

            val intent = Intent(it.context, PostActivity::class.java)
            //intent.putExtra("threadId", holder.itemView.findViewById<TextView>(R.id.topicNameId).text)
            //intent.putExtra("threadName", holder.itemView.findViewById<TextView>(R.id.topicNameContainer).text)
            it.context.startActivity(intent)
        }*/
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
        private val binding = CommentContainerBinding.bind(itemView)
        fun onBind(threadItem: CommentItem){
            binding.commentContainer.text = threadItem.threadMessage
        }
    }
}