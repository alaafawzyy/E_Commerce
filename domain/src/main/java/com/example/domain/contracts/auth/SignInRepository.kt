package com.example.domain.contracts.auth

import com.example.domain.common.Resource
import com.example.domain.model.Auth.AuthResponce
import kotlinx.coroutines.flow.Flow

interface SignInRepository {
    suspend fun SignIn(email:String,password:String):Flow<Resource<AuthResponce>>
}