package com.asanme.teachnet.home

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asanme.teachnet.R
import com.asanme.teachnet.databinding.TopicContainerBinding
import com.asanme.teachnet.topics.ForumActivity
import com.asanme.teachnet.model.TopicItem
import com.squareup.picasso.Picasso

/**
 * This class is responsible for updating the topics in realtime and also adding new ones to the RecyclerView in the topic_container.xml file.
 */
class HomeRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val topicItems = mutableListOf<TopicItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TopicItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TopicItemViewHolder).onBind(topicItems[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ForumActivity::class.java)
            intent.putExtra("topicId", holder.itemView.findViewById<TextView>(R.id.userId).text)
            intent.putExtra("topicName", holder.itemView.findViewById<TextView>(R.id.topicNameContainer).text)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return topicItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTopics(topicItems: List<TopicItem>){
        this.topicItems.clear()
        this.topicItems.addAll(topicItems)
        notifyDataSetChanged()
    }

    inner class TopicItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.topic_container, parent, false)
    ){
        private val binding = TopicContainerBinding.bind(itemView)
        fun onBind(topicItem: TopicItem){
            binding.topicId = topicItem.topicId
            binding.topicNameContainer.text = topicItem.topicName
            Picasso.get().load(topicItem.iconUrl).into(binding.topicIcon)
        }
    }
}