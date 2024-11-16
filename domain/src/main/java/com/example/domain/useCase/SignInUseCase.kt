package com.example.domain.useCase

import com.example.domain.common.Resource
import com.example.domain.contracts.auth.SignInRepository
import com.example.domain.model.Auth.AuthResponce
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val signInRepository: SignInRepository) {
    suspend fun invoke(email:String,password:String):Flow<Resource<AuthResponce>>{
        return signInRepository.SignIn(email,password)
    }
}