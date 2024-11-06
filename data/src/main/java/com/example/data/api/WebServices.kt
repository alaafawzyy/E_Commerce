package com.example.data.api

import com.example.data.api.model.CategoryDto
import com.example.data.api.model.Responce
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface WebServices {
    @GET("/api/v1/categories")
    suspend fun getCategories():Responce<List<CategoryDto?>?>

}