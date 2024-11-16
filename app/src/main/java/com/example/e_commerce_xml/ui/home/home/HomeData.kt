package com.example.e_commerce_xml.ui.home.home

import com.example.domain.model.Category
import com.example.domain.model.Products


data class HomeData(
    val categoriesList: List<Category>?,
    val categoryProductsList: List<Products>?,
    val mostSellingProductsList: List<Products>?,

    )