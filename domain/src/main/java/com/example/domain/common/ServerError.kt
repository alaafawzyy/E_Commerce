package com.example.domain.common

import java.lang.Exception

data class ServerError(
    val serverError: String?=null,
    val statusMessage:String?=null,
    val httbException: Throwable?=null
):Throwable(serverError,httbException)
