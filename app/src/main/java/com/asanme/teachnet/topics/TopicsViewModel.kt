package com.asanme.teachnet.topics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asanme.teachnet.model.ForumThread

/**
 * View Model that communicates the ForumActivity with the TopicsController
 */
class ThreadViewModel: ViewModel() {
    private val repository = TopicsController()

    private val _threadData = MutableLiveData<List<ForumThread>>()
    val threadData: LiveData<List<ForumThread>> = _threadData

    fun fetchThreads(filterByTopic:String) {
        repository.fetchThreads(filterByTopic, _threadData)
    }
}