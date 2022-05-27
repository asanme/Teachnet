package com.asanme.teachnet.model

/**
 * Represents a comment in the Topic section
 */
data class CommentItem(
    val threadId: String = "",
    val ownerId: String = "",
    val threadMessage: String = ""
)
