package com.example.data.api.model

import com.example.domain.model.Brand


data class BrandDTO(
	val image: String? = null,
	val createdAt: String? = null,
	val name: String? = null,
	val id: String? = null,
	val slug: String? = null,
	val updatedAt: String? = null,
){
	fun toBrand() : Brand {
		return Brand(image,name,id,slug)
	}
}