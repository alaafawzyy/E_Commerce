package com.example.data.repositories

import com.example.data.repositories.category.CategoriesRepositoryImpl
import com.example.domain.contract.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule{
    
    @Binds
    abstract fun BindCategoriesRepository(categoriesRepositoryImpl: CategoriesRepositoryImpl):CategoriesRepository
}