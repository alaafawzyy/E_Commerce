package com.example.domain.useCase

import com.example.domain.common.Resource
import com.example.domain.contracts.products.ProductsRepository
import com.example.domain.contracts.products.SortedBy
import com.example.domain.model.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMostSellingProductsUseCase @Inject constructor(private val productRepository: ProductsRepository) {

    suspend fun invoke():Flow<Resource<List<Products>?>>{
        return productRepository.getProducts( sortedBy = SortedBy.MOST_SELLING)
    }
}