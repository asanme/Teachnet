package com.asanme.teachnet.model

/**
 * User parameters
 */
data class User(
    var uid:String = "",
    var email: String = "",
    var name:String = "",
    var surname:String = "",
    var username:String = "",
    var profilePictureUrl: String = ""
)