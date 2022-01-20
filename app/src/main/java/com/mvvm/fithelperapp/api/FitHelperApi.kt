package com.mvvm.fithelperapp.api

import com.mvvm.fithelperapp.api.ApiEntities.ApiResponse
import com.mvvm.fithelperapp.api.ApiEntities.CategoryApiEntity
import com.mvvm.fithelperapp.api.ApiEntities.RecipeApiEntity
import retrofit2.Call
import retrofit2.http.GET

interface FitHelperApi {


    @GET("api/getRecipes.php")
    suspend fun getRecipes() : List<RecipeApiEntity>

    @GET("api/getCategories.php")
    suspend fun getCategories() : List<CategoryApiEntity>

    @GET("api/getEverything.php")
    suspend fun getEverything() : List<ApiResponse>

    companion object{
        //const val BASE_URL = "http://192.168.0.93/"
        const val BASE_URL = "http://10.0.2.2/"
    }
}

