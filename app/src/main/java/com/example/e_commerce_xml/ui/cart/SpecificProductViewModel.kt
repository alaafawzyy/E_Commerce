package com.example.e_commerce_xml.ui.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.model.Products
import com.example.domain.useCase.CategoryProductsUseCase
import com.example.domain.useCase.GetMostSellingProductsUseCase
import com.example.domain.useCase.CategoriesUseCase
import com.example.domain.useCase.SpecificProductUseCase
import com.example.e_commerce_xml.utils.SingleLiveEvent
import com.example.e_commerce_xml.ui.base.BaseViewModel
import com.example.e_commerce_xml.ui.login.LoginContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecificProductViewModel @Inject constructor(private val specificProductUseCase: SpecificProductUseCase):BaseViewModel() {
    private val _product = MutableStateFlow<Products?>(null)
    val product: StateFlow<Products?> get() = _product
     fun getProduct(id:String){
        viewModelScope.launch {
            specificProductUseCase.invoke(id).collect{
                when(it){
                    is Resource.Success ->{
                        val result = it.data
                        _product.value = result
                    }
                    else ->{

                    }
                }
            }
        }


}}
