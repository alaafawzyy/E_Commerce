package com.example.data.dataSource.category

import com.example.data.contract.category.CategoryOnlineDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CategoriesOnlineDataSourceModulue {
    @Binds
    abstract fun BindsCategoriesOnlineDataSource(impl:CategoriesOnlineDataSourceImpl):CategoryOnlineDataSource
}