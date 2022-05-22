package com.asanme.teachnet.forum_controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asanme.teachnet.model.ForumThread

class ThreadViewModel: ViewModel() {
    private val repository = ThreadController()

    private val _threadData = MutableLiveData<List<ForumThread>>()
    val threadData: LiveData<List<ForumThread>> = _threadData

    fun fetchThreads() {
        repository.fetchThreads(_threadData)
    }
}