package com.example.data.dataSource.products

import com.example.data.api.webServices.CategoryWebServices
import com.example.data.contract.products.ProductOnlineDataSource
import com.example.data.executeApi
import com.example.domain.contracts.products.SortedBy
import com.example.domain.model.Auth.AuthResponce
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
    }

    override suspend fun getSpecificProduct(productId: String): Products {
        val responce= executeApi {  categoryWebServices.getSpecficProduct(productId) }
        val prResponce= Products(
            sold = responce.data?.sold,
            images = responce.data?.images,
            quantity = responce.data?.quantity,
            imageCover = responce.data?.imageCover,
            description =responce.data?.description ,
            title = responce.data?.title,
            ratingsQuantity =responce.data?.ratingsQuantity ,
            ratingsAverage =responce.data?.ratingsAverage ,
            createdAt =responce.data?.createdAt ,
            price =responce.data?.price ,
            id =responce.data?.id,
            category =responce.data?.category?.toCategory() ,
            priceAfterDiscount = responce.data?.priceAfterDiscount,
            brand =responce.data?.brand?.toBrand(),
            slug = responce.data?.slug,
            updatedAt =responce.data?.updatedAt ,


        )
        return prResponce
    }

}
