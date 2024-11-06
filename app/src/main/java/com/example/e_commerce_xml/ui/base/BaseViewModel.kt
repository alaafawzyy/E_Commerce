package com.example.e_commerce_xml.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.common.InternetConnectionError
import com.example.domain.common.Resource
import com.example.domain.model.Category

open class BaseViewModel :ViewModel(){
    val viewMessage= MutableLiveData<ViewMessage>()
    val showLoading= MutableLiveData<Boolean>()

    fun <T>handleResource(resource: Resource<T>){
        when (resource){
            is Resource.loading ->{
                showLoading.postValue(true)
            }
            is Resource.ServerFail->{
                viewMessage.postValue(ViewMessage(message = resource.error.message?:"somthing went wrong"))
            }
            is Resource.Fail->{
                when(resource.exception) {
                    is InternetConnectionError -> {
                        viewMessage.postValue(ViewMessage(message = "Please check your interner connection"))
                    }

                    else ->{
                        viewMessage.postValue(ViewMessage(message = resource.exception.message?:"Please check your interner connection"))
                    }
                }}
            else ->{}
        }
    }

}