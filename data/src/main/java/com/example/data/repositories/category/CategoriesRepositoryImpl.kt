package com.example.data.repositories.category

import com.example.data.contract.category.CategoryOnlineDataSource
import com.example.data.toFlow
import com.example.domain.common.Resource
import com.example.domain.contract.CategoriesRepository
import com.example.domain.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepositoryImpl
    @Inject constructor(private val categoryOnlineDataSource: CategoryOnlineDataSource) :CategoriesRepository {

    override suspend fun getAllCategories(): Flow<Resource<List<Category>?>> {
        return toFlow { categoryOnlineDataSource.getAllCategories() }
    }

}
//انا هنا هفضل مخلياها ترجعلي flow رغم الداتا بترجع list<caategory>
//هعمل فانكشن تاخد الليست وترجعلي فلو