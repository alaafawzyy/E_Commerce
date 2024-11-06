package com.example.data.api.model


import com.google.gson.annotations.SerializedName

data class Responce<T>(
    @SerializedName("data")
    val `data`: T?,
    @SerializedName("metadata")
    val metadata: Pagination?,
    @SerializedName("results")
    val results: Int?,

    @SerializedName("statusMsg")
    val statusMsg: String?,
     @SerializedName("message")
     val message:String?,
)