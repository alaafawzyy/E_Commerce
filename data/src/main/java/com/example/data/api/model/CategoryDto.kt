package com.example.data.api.model




import com.example.domain.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
) {
    fun toCategory(): Category {
        return Category(id,image,name,slug)
    }
}