package com.example.data.dataSource.category

import android.util.Log
import com.example.data.api.webServices.CategoryWebServices
import com.example.data.contract.category.CategoryOnlineDataSource
import com.example.data.executeApi
import com.example.domain.model.Category
import javax.inject.Inject

class CategoriesOnlineDataSourceImpl @Inject constructor(private val webServices: CategoryWebServices):
    CategoryOnlineDataSource {
     override suspend fun getAllCategories(): List<Category>? {
        val responce= executeApi { webServices.getCategories()}
            return responce.data?.filterNotNull()?.map {
                it.toCategory()
            }

        }
    }

