package com.example.data.repositories.categoryProducts


import com.example.data.contract.categoryproducts.CategoryProductsOnlineDatasource
import com.example.data.toFlow
import com.example.domain.common.Resource
import com.example.domain.contracts.categoryproducts.CategoryProductsRepository
import com.example.domain.model.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

 class CategoryProductsRepositoryImpl  @Inject constructor(
    private val categoryProductsOnlineDatasource: CategoryProductsOnlineDatasource
): CategoryProductsRepository
{
    override suspend fun getCategoryProducts(): Flow<Resource<List<Products>?>> {
        return toFlow{ categoryProductsOnlineDatasource.getCategoryProducts() }
    }
}