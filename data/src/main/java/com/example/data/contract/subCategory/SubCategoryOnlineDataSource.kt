package com.example.data.contract.subCategory

import com.example.domain.common.Resource
import com.example.domain.model.SubCategory
import kotlinx.coroutines.flow.Flow

interface SubCategoryOnlineDataSource {
    suspend fun getSubCategoriesByCategoryId(categoryId: String): List<SubCategory>?
}