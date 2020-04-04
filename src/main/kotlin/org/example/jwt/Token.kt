package org.example.jwt

import com.google.gson.annotations.SerializedName

data class Token (
    @SerializedName("token")
    var token: String? = null
)