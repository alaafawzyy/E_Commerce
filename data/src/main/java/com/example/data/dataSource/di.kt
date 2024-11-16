package com.example.data.dataSource

import com.example.data.contract.auth.SignInOnlineDataSource
import com.example.data.contract.category.CategoryOnlineDataSource
import com.example.data.contract.categoryproducts.CategoryProductsOnlineDatasource
import com.example.data.contract.products.ProductOnlineDataSource
import com.example.data.contract.subCategory.SubCategoryOnlineDataSource
import com.example.data.dataSource.auth.SignInOnlineDataSourceImpl
import com.example.data.dataSource.category.CategoriesOnlineDataSourceImpl
import com.example.data.dataSource.categoryproducts.CategoryProductsOnlineDatasourceImpl
import com.example.data.dataSource.products.ProductsOnlineDatasourceImpl
import com.example.data.dataSource.subCategory.SubCategoryOnlineDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class OnlineDataSourceModulue {

    @Binds
    abstract fun provideBindsCategoriesOnlineDataSource(impl: CategoriesOnlineDataSourceImpl): CategoryOnlineDataSource

    @Binds
    abstract fun provideBindsProductsOnlineDataSource(impl: ProductsOnlineDatasourceImpl): ProductOnlineDataSource

    @Binds
    abstract fun provideCategoryProductsDatasource(impl : CategoryProductsOnlineDatasourceImpl)
            : CategoryProductsOnlineDatasource

    @Binds
    abstract fun provideAuthDatasource(impl : SignInOnlineDataSourceImpl): SignInOnlineDataSource

    @Binds
    abstract fun provideSubCategoriesDatasource(impl : SubCategoryOnlineDataSourceImpl):SubCategoryOnlineDataSource

}

