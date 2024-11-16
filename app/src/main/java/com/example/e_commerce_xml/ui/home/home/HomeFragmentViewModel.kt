package com.example.e_commerce_xml.ui.home.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.useCase.CategoryProductsUseCase
import com.example.domain.useCase.GetMostSellingProductsUseCase
import com.example.domain.useCase.CategoriesUseCase
import com.example.e_commerce_xml.utils.SingleLiveEvent
import com.example.e_commerce_xml.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val categoryUseCase: CategoriesUseCase,
    private val mostSellingProductsUseCase: GetMostSellingProductsUseCase,
    private val categoryProductsUseCase : CategoryProductsUseCase,

    ):BaseViewModel(), HomeContract.ViewModel{

    private val _state= MutableStateFlow<HomeContract.State>(HomeContract.State.Loading)
    private val _event= SingleLiveEvent<HomeContract.Event>()


    override val state: StateFlow<HomeContract.State>
        get() = _state

    override val event: SingleLiveEvent<HomeContract.Event>
        get() = _event


    override fun doAction(action: HomeContract.Action) {
        when (action) {
            is HomeContract.Action.InitPage -> {
                loadData()
            }

        }
    }
    private fun loadData(){
        viewModelScope.launch {
            combine(
                categoryUseCase.invoke(),
                categoryProductsUseCase.invoke(),
                mostSellingProductsUseCase.invoke(),
            ){categories , categoryProducts , mostSellingProducts ->
                var data : HomeData?= null
                if(categories is Resource.Success &&
                    categoryProducts is Resource.Success &&
                    mostSellingProducts is Resource.Success ){
                    data = HomeData(
                            categoriesList = categories.data,
                            categoryProductsList = categoryProducts.data,
                            mostSellingProductsList = mostSellingProducts.data,
                        )
                }
                if(categories is Resource.Fail || categories is Resource.ServerFail){
                    extractViewMessage(categories)?.let {
                        _event.postValue(HomeContract.Event.ShowMessage(it))
                    }
                }
                if(categoryProducts is Resource.Fail || categoryProducts is Resource.ServerFail){
                    extractViewMessage(categoryProducts)?.let {
                        _event.postValue(HomeContract.Event.ShowMessage(it))
                    }
                }
                if(mostSellingProducts is Resource.Fail || mostSellingProducts is Resource.Fail){
                    extractViewMessage(mostSellingProducts)?.let {
                        _event.postValue(HomeContract.Event.ShowMessage(it))
                    }
                }
                data
            }.collect{
                Log.d("TAG","in collect in view model$it")
                it?.let {
                    _state.emit(
                        HomeContract.State.Success(
                            it.categoriesList,it.categoryProductsList,it.mostSellingProductsList
                        )
                    )
                }
            }
        }
    }


}
