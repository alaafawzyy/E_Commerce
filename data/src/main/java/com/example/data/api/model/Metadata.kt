package com.example.data.api.model


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("currentPage")
    val currentPage: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("numberOfPages")
    val numberOfPages: Int?
)