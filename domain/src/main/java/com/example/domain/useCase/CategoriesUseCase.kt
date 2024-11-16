package com.example.domain.useCase

import com.example.domain.common.Resource
import com.example.domain.contracts.category.CategoriesRepository
import com.example.domain.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository) {

    suspend fun invoke():Flow<Resource<List<Category>?>>{
        return categoriesRepository.getAllCategories()
    }
}
