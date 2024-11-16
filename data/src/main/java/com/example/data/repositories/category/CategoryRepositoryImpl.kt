package com.example.data.repositories.category


import com.example.data.contract.category.CategoryOnlineDataSource
import com.example.data.toFlow
import com.example.domain.common.Resource
import com.example.domain.contracts.category.CategoriesRepository
import com.example.domain.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryOnlineDatasource: CategoryOnlineDataSource
) : CategoriesRepository {
    override suspend fun getAllCategories(): Flow<Resource<List<Category>?>> {
        return toFlow {
             categoryOnlineDatasource.getAllCategories()
        }
    }
}