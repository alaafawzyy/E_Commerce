package com.example.e_commerce_xml.ui.Categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.useCase.CategoriesUseCase
import com.example.domain.useCase.SubCategoryUseCase
import com.example.e_commerce_xml.ui.base.BaseViewModel
import com.example.e_commerce_xml.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewHolder @Inject constructor(private val categoryUseCase: CategoriesUseCase,
    private val subCategoryUseCase: SubCategoryUseCase) :BaseViewModel(),CategoryContract.ViewModel{

    override fun doAction(action: CategoryContract.Action) {
       when(action){
           is CategoryContract.Action.InitPage ->{
               loadData()
           }
       }
    }

    private val _state= MutableStateFlow<CategoryContract.State>(CategoryContract.State.Loading)
    private val _event= SingleLiveEvent<CategoryContract.Event>()
    override val event: LiveData<CategoryContract.Event>
        get() = _event
    override val state: StateFlow<CategoryContract.State>
        get() = _state

fun onCategorySelected( id:String){
    loadSubCategory(id)

}

    private fun loadSubCategory(id: String) {
        viewModelScope.launch {
            subCategoryUseCase.invoke(id).collect{resource ->
                when(resource) {
                    is Resource.Success -> {
                        _state.emit(CategoryContract.State.SubCategory(resource.data,))
                    }

                    else -> {
                        extractViewMessage(resource)
                            ?.let {
                                _event.postValue(CategoryContract.Event.ShowMessage(it))
                            }
                    }
                }}}}

    private fun loadData() {
        viewModelScope.launch {
            categoryUseCase.invoke().collect{resource ->
            when(resource){
                is Resource.Success->{
                    _state.emit(CategoryContract.State.Success(resource.data,))
                }else->{
                extractViewMessage(resource)
                    ?.let {
                        _event.postValue(CategoryContract.Event.ShowMessage(it))
                    }
            }
            }
        }

        }
    }

}