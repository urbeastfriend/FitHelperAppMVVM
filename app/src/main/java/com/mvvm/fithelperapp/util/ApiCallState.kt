package com.mvvm.fithelperapp.util

import retrofit2.HttpException

sealed class ApiCallState<out R> {

    object Success : ApiCallState<Nothing>()
    data class Error(val exception: HttpException) : ApiCallState<Nothing>()
    object Loading : ApiCallState<Nothing>()
}