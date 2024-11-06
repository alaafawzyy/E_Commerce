package com.example.domain.contract


import com.example.domain.common.Resource
import com.example.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getAllCategories():Flow<Resource<List<Category>?>>
}