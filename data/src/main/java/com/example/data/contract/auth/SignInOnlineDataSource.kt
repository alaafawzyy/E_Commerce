package com.example.data.contract.auth

import com.example.domain.model.Auth.AuthResponce

interface SignInOnlineDataSource {
    suspend fun SignIn(email:String,password:String):AuthResponce
}