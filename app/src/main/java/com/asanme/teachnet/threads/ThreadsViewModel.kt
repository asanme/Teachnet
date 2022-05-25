package com.asanme.teachnet.threads

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asanme.teachnet.model.CommentItem

class ThreadsViewModel: ViewModel() {
    private val repository = PostController()

    private val _threadData = MutableLiveData<List<CommentItem>>()
    val threadData: LiveData<List<CommentItem>> = _threadData

    fun fetchComents(filterByPostId:String) {
        repository.fetchComments(filterByPostId, _threadData)
    }
}