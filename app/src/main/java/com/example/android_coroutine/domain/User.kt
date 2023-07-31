package com.example.android_coroutine.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("user_name") val username: String,
    @Expose(serialize = false, deserialize = false) val password: String,
    @SerializedName("display_name") val displayName: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("email") val email: String
)