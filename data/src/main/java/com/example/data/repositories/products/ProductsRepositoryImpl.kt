package com.route.data.repository.products


import com.example.data.contract.products.ProductOnlineDataSource
import com.example.data.toFlow
import com.example.domain.common.Resource
import com.example.domain.contracts.products.ProductsRepository
import com.example.domain.contracts.products.SortedBy
import com.example.domain.model.Products
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsOnlineDatasource : ProductOnlineDataSource
)
    : ProductsRepository {
    override suspend fun getProducts(
        categoryId: String?,
        sortedBy: SortedBy?,
        brandId: String?,
    ): Flow<Resource<List<Products>?>> {
        return toFlow{ productsOnlineDatasource.getProducts(sortedBy, categoryId, brandId) }
    }

}