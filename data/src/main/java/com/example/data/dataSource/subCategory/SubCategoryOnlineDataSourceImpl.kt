package com.example.data.dataSource.subCategory

import com.example.data.api.webServices.CategoryWebServices
import com.example.data.contract.subCategory.SubCategoryOnlineDataSource
import com.example.data.executeApi
import com.example.domain.common.Resource
import com.example.domain.model.SubCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubCategoryOnlineDataSourceImpl @Inject constructor(private val webServices: CategoryWebServices):SubCategoryOnlineDataSource {
    override suspend fun getSubCategoriesByCategoryId(categoryId: String): List<SubCategory>? {

        val responce= executeApi { webServices.getSubCategoriesByCategoryId(categoryId = categoryId)}


        return responce.data?.filterNotNull()?.map { it.toSubCategory()}

    }
    }
