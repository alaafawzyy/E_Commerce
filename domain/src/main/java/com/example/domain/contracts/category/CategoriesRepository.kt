package com.example.domain.contracts.category

import com.example.domain.common.Resource
import com.example.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getAllCategories() : Flow<Resource<List<Category>?>>
}