package com.example.e_commerce_xml.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.model.Category
import com.example.domain.useCase.getCategoriesUseCase
import com.example.e_commerce_xml.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val getCategoriesUseCase: getCategoriesUseCase):BaseViewModel() {

    val categories= MutableLiveData<List<Category>?>()


    fun gettCategories(){
          viewModelScope.launch (Dispatchers.IO){
              getCategoriesUseCase.invoke()
                  .collect{resource ->
                      when(resource){
                          is Resource.Success->{
                              categories.postValue(resource.data)
                          }else->{
                              handleResource(resource)
                          }
                      }
              }
          }
    }
}
