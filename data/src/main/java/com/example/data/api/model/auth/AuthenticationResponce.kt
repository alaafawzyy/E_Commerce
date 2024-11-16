package com.example.data.api.model.auth


import com.google.gson.annotations.SerializedName

data class AuthenticationResponce(
    @SerializedName("message")
    val message: String?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: UserDto?
)