package com.example.e_commerce_xml.ui.home.home

import androidx.lifecycle.LiveData
import com.example.domain.model.Category
import com.example.domain.model.Products
import com.example.e_commerce_xml.utils.ViewMessage
import kotlinx.coroutines.flow.StateFlow

class HomeContract {

    interface ViewModel {
        fun doAction(action : Action)
        val event : LiveData<Event>
        val state : StateFlow<State>
    }

    sealed class Action { //intent
        data object InitPage : Action() }

    sealed class Event {
        data class ShowMessage(val message : ViewMessage) : Event() }

    sealed class State {
        data object Loading : State()
        data class Success(
            val categoriesList: List<Category>? = null,
            val mostSellingProductsList: List<Products>? = null,
            val categoryProductsList: List<Products>? = null
        ) : State()
    }
}