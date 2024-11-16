package com.example.data.repositories.subCategory

import com.example.data.contract.subCategory.SubCategoryOnlineDataSource
import com.example.data.toFlow
import com.example.domain.common.Resource
import com.example.domain.contracts.subCategory.SubCategoryRepo
import com.example.domain.model.SubCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubCategoryRepoImpl @Inject constructor(private val source: SubCategoryOnlineDataSource):
    SubCategoryRepo {
    override suspend fun getSubCategoriesByCategoryId(categoryId: String): Flow<Resource<List<SubCategory>?>> {
       return toFlow {  source.getSubCategoriesByCategoryId(categoryId) }
    }
}