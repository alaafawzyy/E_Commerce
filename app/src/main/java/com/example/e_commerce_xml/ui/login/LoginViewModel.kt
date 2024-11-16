package com.example.e_commerce_xml.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.useCase.SignInUseCase
import com.example.e_commerce_xml.utils.SingleLiveEvent
import com.example.e_commerce_xml.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val signInUseCase: SignInUseCase) :BaseViewModel(),LoginContract.ViewModel {
        val emailLiveData=MutableLiveData<String>()
        val passwordLiveData=MutableLiveData<String>()
        val emailErrorLiveData=MutableLiveData<String?>()
        val passwordErrorLiveData=MutableLiveData<String?>()

    override fun doAction(action: LoginContract.Action) {
        when(action){
            LoginContract.Action.Login->{
              login()
            }
        }
    }

    private val _state=MutableStateFlow<LoginContract.State>(LoginContract.State.Loading)
    private val _event=
        SingleLiveEvent<LoginContract.Event>()
    override val event: LiveData<LoginContract.Event>
        get() = _event
    override val state: StateFlow<LoginContract.State>
        get() =_state



    private fun login() {
       if(!isValid()) return
        viewModelScope.launch (Dispatchers.IO){
            _state.emit(LoginContract.State.Loading)
            signInUseCase.invoke(emailLiveData.value!!,passwordLiveData.value!!)
                .collect{ responce->
                    when(responce){
                        is Resource.Success -> _state.emit(LoginContract.State.Success(responce.data))
                        else ->{
                            extractViewMessage(responce)?.let {
                                _event.postValue(LoginContract.Event.ShowMessage(it))
                            }
                        }
                    }

                }
        }
    }

    fun isValid():Boolean{
        var isvalid=true
        if (emailLiveData.value.isNullOrBlank()){
            emailErrorLiveData.value="Please Enter Your Email"
            isvalid=false
        }else{
            emailErrorLiveData.value=null
        }

        if (passwordLiveData.value.isNullOrBlank()){
            passwordErrorLiveData.value="Please Enter Your UserName"
            isvalid=false
        }else{
            passwordErrorLiveData.value=null
        }
        return isvalid
    }
}