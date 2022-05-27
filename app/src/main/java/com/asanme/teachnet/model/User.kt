package com.asanme.teachnet.model

/**
 * Represents a user
 */
data class User(
    var email: String = "",
    var name:String = "",
    var profilePictureUrl: String = "",
    var surname:String = "",
    var uid:String = "",
    var username:String = ""
)