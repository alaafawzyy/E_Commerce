package com.example.data

import android.telephony.TelephonyManager.TimeoutException
import com.example.data.api.model.Responce
import com.example.domain.common.InternetConnectionError
import com.example.domain.common.Resource
import com.example.domain.common.ServerError
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.isActive
import okio.IOException
import retrofit2.HttpException


suspend fun <T>executeApi(apiCall:suspend ()->T):T{
    try {
        val responce=apiCall.invoke()
        return responce

    }catch (ex:HttpException){
        if (ex.code() in 400..600){
            val serverResponce=ex.response()?.errorBody()?.string()
            val responce=Gson().fromJson<Responce<Any>>(serverResponce,Responce::class.java)
            throw ServerError(responce.message,responce.statusMsg,ex)
        }
        throw ex
    }

    catch (ex:IOException){
         throw InternetConnectionError(ex)
    }
    catch (ex:Exception){
        throw ex
    }

}



suspend fun <T>toFlow(getData:suspend ()->T): Flow<Resource<T>> {
return flow {
emit(Resource.loading)
    val responce=getData.invoke()
    emit(Resource.Success(responce))
}
    .catch {ex->
        when(ex){
            is ServerError ->{
                emit(Resource.ServerFail(ex))
            }
            is InternetConnectionError -> {
                emit((Resource.Fail(ex)))
            }
            else ->emit(Resource.Fail(ex))
        }

    }
}