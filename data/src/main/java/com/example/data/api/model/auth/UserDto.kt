package com.example.data.api.model.auth


import com.example.domain.model.Auth.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("role")
    val role: String?
) {
    fun toUser():User{
      return User(email,name, role)
    }
}