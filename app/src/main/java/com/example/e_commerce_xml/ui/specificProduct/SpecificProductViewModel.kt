package com.example.e_commerce_xml.ui.specificProduct

import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.model.Products
import com.example.domain.useCase.SpecificProductUseCase
import com.example.e_commerce_xml.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
