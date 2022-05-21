package com.asanme.teachnet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asanme.teachnet.databinding.TopicContainerBinding
import com.asanme.teachnet.model.TopicItem

class TopicRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val topicItems = mutableListOf<TopicItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TopicViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TopicViewHolder).onBind(topicItems[position])
    }

    override fun getItemCount(): Int {
        return topicItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTopicItems(topicItems : List<TopicItem>){
        this.topicItems.clear()
        this.topicItems.addAll(topicItems)
        notifyDataSetChanged()
    }

    inner class TopicViewHolder (parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.topic_container, parent, false)
    ) {
        private val binding = TopicContainerBinding.bind(itemView)

        fun onBind(topicItem: TopicItem){
            binding.topicName.text = topicItem.topicName
        }
    }
}