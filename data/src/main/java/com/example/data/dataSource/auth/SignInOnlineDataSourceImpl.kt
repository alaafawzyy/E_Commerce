package com.example.data.dataSource.auth

import com.example.data.api.webServices.AushWebServices
import com.example.data.contract.auth.SignInOnlineDataSource
import com.example.data.executeApi
import com.example.domain.model.Auth.AuthResponce
import javax.inject.Inject

class SignInOnlineDataSourceImpl @Inject constructor(private val authWebServices: AushWebServices):SignInOnlineDataSource {
    override suspend fun SignIn(email: String, password: String): AuthResponce {
        val responce= executeApi {  authWebServices.getSignIn(email,password) }
         val authResponce=AuthResponce(responce.token,responce.user?.toUser(),)
   return authResponce
    }

}