package com.example.domain.useCase

import com.example.domain.common.Resource
import com.example.domain.contracts.categoryproducts.CategoryProductsRepository
import com.example.domain.model.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryProductsUseCase @Inject constructor(
    private val categoryProductsRepository: CategoryProductsRepository
){
    suspend fun invoke() : Flow<Resource<List<Products>?>> {
        return categoryProductsRepository.getCategoryProducts()
    }

}