package com.example.domain.contracts.products

import com.example.domain.common.Resource
import com.example.domain.model.Products

import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(
        categoryId : String?=null,
        sortedBy : SortedBy?=null,
        brandId : String?=null
    ) : Flow<Resource<List<Products>?>>



}
enum class SortedBy (val value : String){
    PRICE_DESC("-price"),
    PRICE_ASC("price"),
    MOST_SELLING("-sold")
}