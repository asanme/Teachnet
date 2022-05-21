package com.asanme.teachnet

import androidx.recyclerview.widget.RecyclerView
import com.asanme.teachnet.databinding.TopicContainerBinding

class TopicViewHolder (private val cardCellBinding: TopicContainerBinding) : RecyclerView.ViewHolder(cardCellBinding.root) {
    //TODO Load BitMap from URL and parse it into the ImageView
    fun bindTopic(topic : Topic){
        cardCellBinding.topicName.text = topic.topicName
        //cardCellBinding.topicIcon.setImageURI(product.productName)
    }
}

