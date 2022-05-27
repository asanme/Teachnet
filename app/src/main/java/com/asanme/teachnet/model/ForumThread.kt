package com.asanme.teachnet.model

/**
 * Represents a thread used in the Post Activity
 */
data class ForumThread(
    val threadId : String = "",
    val threadOwnerId: String = "",
    val threadTitle: String = "",
    val threadMessage: String = "",
    val threadTopic: String = ""
)