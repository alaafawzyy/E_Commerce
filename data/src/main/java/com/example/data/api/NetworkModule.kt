package com.example.data.api

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideLoggingInterceptor():HttpLoggingInterceptor{
        val loggingInterceptor=HttpLoggingInterceptor{
            Log.e("retrofit","$it")
        }
        loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    fun providehttpclient( loggingInterceptor: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    @Provides
    fun provideConvertorFactory( loggingInterceptor: HttpLoggingInterceptor):GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit( httpClient: OkHttpClient,converterFactory: GsonConverterFactory):Retrofit{
        return Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .baseUrl("https://ecommerce.routemisr.com")
            .build()
    }

    @Provides
    fun provideWebServices( retrofit: Retrofit):WebServices{
        return retrofit.create(WebServices::class.java)
    }
}