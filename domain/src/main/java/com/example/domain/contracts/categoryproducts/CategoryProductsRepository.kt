package com.example.domain.contracts.categoryproducts

import com.example.domain.common.Resource
import com.example.domain.model.Products
import kotlinx.coroutines.flow.Flow

interface CategoryProductsRepository {

    suspend fun getCategoryProducts() :Flow<Resource<List<Products>?>>
}