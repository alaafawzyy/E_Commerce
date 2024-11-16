package com.example.data.contract.categoryproducts


import com.example.domain.model.Products

interface CategoryProductsOnlineDatasource {
    suspend fun getCategoryProducts() : List<Products>?
}