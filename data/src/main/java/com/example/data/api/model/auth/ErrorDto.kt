package com.example.data.api.model.auth


import com.google.gson.annotations.SerializedName

data class ErrorDto(
    @SerializedName("message")
    val message: String?,
    @SerializedName("statusMsg")
    val statusMsg: String?
)