package org.example.data_classes.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginUser: Serializable {

    @SerializedName("username")
    var username: String? = null

    @SerializedName("password")
    var password: String? = null


    constructor() {}

    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }

    companion object{
        private const val serialVersionUID = -1764970284520387975L
    }
}