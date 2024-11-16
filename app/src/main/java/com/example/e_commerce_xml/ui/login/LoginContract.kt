package com.example.e_commerce_xml.ui.login

import androidx.lifecycle.LiveData
import com.example.domain.model.Auth.AuthResponce
import com.example.e_commerce_xml.utils.ViewMessage
import kotlinx.coroutines.flow.StateFlow

class LoginContract {
    interface ViewModel {
        fun doAction(action : Action)
        val event : LiveData<Event>
        val state : StateFlow<State>
    }

    sealed class Action {
        data object Login : Action()
    }

    sealed class Event {
        data class ShowMessage(val message : ViewMessage) : Event() }

    sealed class State {
        data object Loading : State()
        data class Success(val user: AuthResponce, ) : State()
    }
}