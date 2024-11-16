package com.example.domain.contracts.subCategory

import com.example.domain.common.Resource
import com.example.domain.model.SubCategory
import kotlinx.coroutines.flow.Flow

interface SubCategoryRepo {
    suspend fun getSubCategoriesByCategoryId(categoryId: String):Flow<Resource< List<SubCategory>?>>
}