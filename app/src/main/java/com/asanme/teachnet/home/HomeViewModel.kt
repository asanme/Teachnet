package com.asanme.teachnet.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asanme.teachnet.model.TopicItem

/**
 * View Model that communicates the HomeActivity with the HomeController
 */
class HomeViewModel: ViewModel() {
    private val repository = HomeController()

    private val _topicData = MutableLiveData<List<TopicItem>>()
    val topicData: LiveData<List<TopicItem>> = _topicData

    fun fetchTopics() {
        repository.fetchTopics(_topicData)
    }
}