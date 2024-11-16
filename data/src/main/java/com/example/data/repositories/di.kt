package com.example.data.repositories

import com.example.data.repositories.auth.SignInRepoImpl
import com.example.data.repositories.category.CategoryRepositoryImpl
import com.example.data.repositories.categoryProducts.CategoryProductsRepositoryImpl
import com.example.data.repositories.subCategory.SubCategoryRepoImpl
import com.example.domain.contracts.auth.SignInRepository
import com.example.domain.contracts.category.CategoriesRepository
import com.example.domain.contracts.categoryproducts.CategoryProductsRepository
import com.example.domain.contracts.products.ProductsRepository
import com.example.domain.contracts.subCategory.SubCategoryRepo
import com.route.data.repository.products.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule{
    
    @Binds
    abstract fun BindCategoriesRepository(categoriesRepositoryImpl: CategoryRepositoryImpl): CategoriesRepository

    @Binds
    abstract fun BindProductsRepository(productsRepositoryImpl: ProductsRepositoryImpl):ProductsRepository

    @Binds
    abstract fun BindcategoryProduct(categoryProductImpl: CategoryProductsRepositoryImpl):CategoryProductsRepository

    @Binds
    abstract fun BindSignIn(categoryProductImpl: SignInRepoImpl):SignInRepository

    @Binds
    abstract fun BindSubCategory(subCategoryRepositoryImpl: SubCategoryRepoImpl):SubCategoryRepo

}