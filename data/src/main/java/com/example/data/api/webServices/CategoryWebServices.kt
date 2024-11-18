package com.example.data.api.webServices

import com.example.data.api.model.CategoryDTO
import com.example.data.api.model.ProductsDTO
import com.example.data.api.model.Response
import com.example.data.api.model.SubcategoryDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryWebServices {
    @GET("/api/v1/products")
    suspend fun getProducts(
        @Query("sort") sortedBy:String?=null,
        @Query("brand") brand:String?=null,
        @Query("category") category:String?=null
    ) : Response<List<ProductsDTO?>?>


    @GET("/api/v1/categories")
    suspend fun getCategories() : Response<List<CategoryDTO?>?>


    @GET("/api/v1/products")
    suspend fun getCategoryProducts() : Response<List<ProductsDTO?>?>

    @GET("/api/v1/categories/{categoryId}/subcategories")
    suspend fun getSubCategoriesByCategoryId(@Path("categoryId") categoryId: String): Response<List<SubcategoryDTO?>?>

    @GET("/api/v1/products/{productId}/")
    suspend fun getSpecficProduct(@Path("productId") productId: String): Response<ProductsDTO?>

}