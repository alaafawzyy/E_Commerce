package com.example.data.dataSource.category

import com.example.data.api.WebServices
import com.example.data.contract.category.CategoryOnlineDataSource
import com.example.data.executeApi
import com.example.domain.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoriesOnlineDataSourceImpl @Inject constructor(private val webServices: WebServices):CategoryOnlineDataSource {
    override suspend fun getAllCategories(): List<Category>? {
        val responce= executeApi { webServices.getCategories()}
            return responce.data?.filterNotNull()?.map {
                it.toCategory()
            }

        }
    }


/*
في الـ Data Source (CategoriesOnlineDataSourceImpl)، الداتا بترجع كـ List<Category>? لأنك بتجيبها مرة واحدة من الـ API.
في الـ Repository (CategoriesRepository)، بتستخدم Flow لأنك ممكن تكون عايز تتابع التغييرات بشكل ديناميكي، أو تستخدم كاش، أو تجمع داتا من مصادر مختلفة.
 */
