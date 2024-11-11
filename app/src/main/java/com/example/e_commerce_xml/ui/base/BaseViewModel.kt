package com.example.e_commerce_xml.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.common.InternetConnectionError
import com.example.domain.common.Resource
import com.example.domain.model.Category

open class BaseViewModel :ViewModel(){
    val viewMessage= MutableLiveData<ViewMessage>()
    val showLoading= MutableLiveData<Boolean>()

    fun <T>extractViewMessage(resource: Resource<T>):ViewMessage?{
        return when (resource){
            is Resource.ServerFail->{
                ViewMessage(message = resource.error.message?:"somthing went wrong")
            }
            is Resource.Fail->{
                when(resource.exception) {
                    is InternetConnectionError -> {
                       ViewMessage(message = "Please check your interner connection")
                    }

                    else ->{
                       ViewMessage(message = resource.exception.message?:"Please check your interner connection")
                    }
                }}
            else ->return null
        }
    }

}