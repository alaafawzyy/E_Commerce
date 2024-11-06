package com.example.domain.useCase

import com.example.domain.common.Resource
import com.example.domain.contract.CategoriesRepository
import com.example.domain.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getCategoriesUseCase @Inject constructor(private val categoriesRepository: CategoriesRepository) {

    suspend fun invoke():Flow<Resource<List<Category>?>>{
        return categoriesRepository.getAllCategories()
    }
}
//بحط فيها اي اوبريشن او اي حاجه عايزة اجبها يعني عامل يوز كيز لل اوفر عامل لل كاتجوري عامل لل..