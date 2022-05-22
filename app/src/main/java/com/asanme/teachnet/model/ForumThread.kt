package com.asanme.teachnet.model

data class ForumThread(
    val threadId : String = "",
    val threadOwnerId: String = "",
    val threadTitle: String = "",
    val threadMessage: String = "",
    val threadTopic: String = ""
)