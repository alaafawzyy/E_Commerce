package com.example.data.contract.category


import com.example.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryOnlineDataSource {

    suspend fun getAllCategories(): List<Category>?
}