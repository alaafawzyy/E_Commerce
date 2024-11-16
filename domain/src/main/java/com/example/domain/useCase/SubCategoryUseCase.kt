package com.example.domain.useCase

import com.example.domain.common.Resource
import com.example.domain.contracts.subCategory.SubCategoryRepo
import com.example.domain.model.SubCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubCategoryUseCase @Inject constructor(private val subCategoryRepo: SubCategoryRepo) {
   suspend fun invoke(id:String): Flow<Resource<List<SubCategory>?>> {
       return subCategoryRepo.getSubCategoriesByCategoryId(id)
   }
}