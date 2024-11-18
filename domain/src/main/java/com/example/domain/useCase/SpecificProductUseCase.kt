package com.example.domain.useCase

import com.example.domain.common.Resource
import com.example.domain.contracts.products.ProductsRepository
import com.example.domain.contracts.products.SortedBy
import com.example.domain.model.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SpecificProductUseCase@Inject constructor(private val productRepository: ProductsRepository) {

    suspend fun invoke(id:String): Flow<Resource<Products>> {
        return productRepository.getSpecifeProducts(id)
    }

}