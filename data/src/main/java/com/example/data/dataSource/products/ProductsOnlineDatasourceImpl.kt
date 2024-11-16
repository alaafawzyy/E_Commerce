package com.example.data.dataSource.products

import com.example.data.api.webServices.CategoryWebServices
import com.example.data.contract.products.ProductOnlineDataSource
import com.example.data.executeApi
import com.example.domain.contracts.products.SortedBy
import com.example.domain.model.Products
import javax.inject.Inject

class ProductsOnlineDatasourceImpl @Inject constructor(
    private val categoryWebServices: CategoryWebServices,
) : ProductOnlineDataSource {
    override suspend fun getProducts(
        sortedBy: SortedBy?,
        categoryId: String?,
        brandId: String?,
    ): List<Products>? {
        return executeApi { categoryWebServices.getProducts(sortedBy?.value, brandId, categoryId) }.data?.map {
            it!!.toProducts()
        }
    } }
