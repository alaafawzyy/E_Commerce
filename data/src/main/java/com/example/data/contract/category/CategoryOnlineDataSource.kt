package com.example.data.contract.category


import com.example.domain.model.Category

interface CategoryOnlineDataSource {

    suspend fun getAllCategories(): List<Category>?
}