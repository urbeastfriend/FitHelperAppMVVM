package com.mvvm.fithelperapp.api

import retrofit2.Call
import retrofit2.http.GET

interface FitHelperApi {


    @GET("api/getRecipes")
    suspend fun getRecipes()
}