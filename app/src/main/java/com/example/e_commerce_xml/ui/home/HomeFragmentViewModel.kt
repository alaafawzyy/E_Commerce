package com.example.e_commerce_xml.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.model.Category
import com.example.domain.useCase.getCategoriesUseCase
import com.example.e_commerce_xml.SingleLiveEvent
import com.example.e_commerce_xml.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val getCategoriesUseCase: getCategoriesUseCase):BaseViewModel()
                                                                       ,HomeContract.ViewModel{


    private fun gettCategories(){
        viewModelScope.launch (Dispatchers.IO){
            getCategoriesUseCase.invoke()
                .collect{resource ->
                    when(resource){
                        is Resource.Success->{
                          _state.emit(HomeContract.State.Succes(categories = resource.data))
                        }else->{
                        extractViewMessage(resource)
                            ?.let {
                                _event.postValue(HomeContract.Event.showMessage(it))
                            }
                    }
                    }
                }
        }
    }
    private val _state= MutableStateFlow<HomeContract.State>(HomeContract.State.Loading)
    private val _event=SingleLiveEvent<HomeContract.Event>()


    override val state: StateFlow<HomeContract.State>
        get() = _state

    override val event: SingleLiveEvent<HomeContract.Event>
        get() = _event


    override fun doAction(action: HomeContract.Action) {
        when(action){
            HomeContract.Action.InitPage ->{
                initPage()
            }
        }

    }
    private fun initPage(){
         gettCategories()
    }
}
