package com.asanme.teachnet.post_controller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asanme.teachnet.model.ForumThread

class PostViewModel: ViewModel() {
    private val repository = PostController()

    private val _threadData = MutableLiveData<List<ForumThread>>()
    val threadData: LiveData<List<ForumThread>> = _threadData

    fun fetchComents(filterByPostId:String) {
        repository.fetchComments(filterByPostId, _threadData)
    }
}