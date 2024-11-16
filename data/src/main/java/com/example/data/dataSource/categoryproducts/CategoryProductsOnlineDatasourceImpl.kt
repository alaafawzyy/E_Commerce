package com.example.data.dataSource.categoryproducts

import com.example.data.api.webServices.CategoryWebServices
import com.example.data.contract.categoryproducts.CategoryProductsOnlineDatasource
import com.example.data.executeApi
import com.example.domain.model.Products
import javax.inject.Inject

class CategoryProductsOnlineDatasourceImpl @Inject constructor(
    private val categoryWebServices: CategoryWebServices
) : CategoryProductsOnlineDatasource {
    override suspend fun getCategoryProducts() : List<Products>? {
       return executeApi {
           categoryWebServices.getCategoryProducts().data?.filterNotNull()?.map {
               it.toProducts()
           }
       }
    }

}