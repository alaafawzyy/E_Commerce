package com.example.data.contract.products

import com.example.domain.contracts.products.SortedBy
import com.example.domain.model.Products


interface ProductOnlineDataSource {
    suspend fun getProducts(sortedBy: SortedBy?,
                            categoryId : String?,
                            brandId : String?) : List<Products>?


}