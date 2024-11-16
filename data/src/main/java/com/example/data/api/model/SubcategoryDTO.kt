package com.example.data.api.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.model.SubCategory


@Parcelize
data class SubcategoryDTO(
	val createdAt: String? = null,
	val name: String? = null,
	val id: String? = null,
	val category: String? = null,
	val slug: String? = null,
	val updatedAt: String? = null
) : Parcelable
{
	fun toSubCategory() :SubCategory{
		return SubCategory(id,category,createdAt,name,slug,updatedAt)
	}
}
