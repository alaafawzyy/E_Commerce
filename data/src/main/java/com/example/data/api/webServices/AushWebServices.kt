package com.example.data.api.webServices

import com.example.data.api.model.auth.AuthenticationResponce
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AushWebServices {
    @FormUrlEncoded
    @POST("/api/v1/auth/signin")
    suspend fun getSignIn(
        @Field("email") email:String,
        @Field ("password") password:String
    ):AuthenticationResponce
}