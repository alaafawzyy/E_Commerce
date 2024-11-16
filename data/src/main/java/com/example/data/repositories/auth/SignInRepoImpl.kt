package com.example.data.repositories.auth

import com.example.data.contract.auth.SignInOnlineDataSource
import com.example.data.toFlow
import com.example.domain.common.Resource
import com.example.domain.contracts.auth.SignInRepository
import com.example.domain.model.Auth.AuthResponce
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInRepoImpl @Inject constructor( private val dataSource: SignInOnlineDataSource): SignInRepository{
    override suspend fun SignIn(email: String, password: String): Flow<Resource<AuthResponce>> {
        return toFlow { dataSource.SignIn(email,password) }
    }
}