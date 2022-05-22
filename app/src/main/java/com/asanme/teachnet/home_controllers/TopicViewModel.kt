package com.asanme.teachnet.home_controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asanme.teachnet.model.TopicItem

class TopicViewModel: ViewModel() {
    private val repository = TopicController()

    private val _topicData = MutableLiveData<List<TopicItem>>()
    val topicData: LiveData<List<TopicItem>> = _topicData

    fun fetchTopics() {
        repository.fetchTopics(_topicData)
    }
}