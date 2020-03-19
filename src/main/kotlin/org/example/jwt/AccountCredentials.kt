package org.example.jwt

import com.google.gson.annotations.SerializedName

data class AccountCredentials(
    @SerializedName("username")
    val username: String = "",
    @SerializedName("password")
    val password: String = "")