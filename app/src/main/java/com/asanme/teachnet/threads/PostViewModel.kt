package com.asanme.teachnet.threads

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asanme.teachnet.model.CommentItem
import com.asanme.teachnet.model.ForumThread

class PostViewModel: ViewModel() {
    private val repository = PostController()

    private val _postData = MutableLiveData<List<ForumThread>>()
    val postData: LiveData<List<ForumThread>> = _postData

    fun fetchPost(filterByPostId:String) {
        repository.fetchPost(filterByPostId, _postData)
    }
}