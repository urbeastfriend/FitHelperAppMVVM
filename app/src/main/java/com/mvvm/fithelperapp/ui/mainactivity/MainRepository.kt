package com.mvvm.fithelperapp.ui.mainactivity

import android.util.Log
import com.mvvm.fithelperapp.api.EntityMappers.CategoryMapper
import com.mvvm.fithelperapp.api.EntityMappers.RecipeMapper
import com.mvvm.fithelperapp.api.FitHelperApi
import com.mvvm.fithelperapp.data.Categories.CategoryDao
import com.mvvm.fithelperapp.data.Recipes.Recipe
import com.mvvm.fithelperapp.data.Recipes.RecipeDao
import com.mvvm.fithelperapp.util.ApiCallState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Retrofit


import javax.inject.Inject


class MainRepository @Inject constructor(
    private val retrofit: FitHelperApi,
    private val recipeDao: RecipeDao,
    private val categoryDao: CategoryDao,
    private val categoryMapper: CategoryMapper,
    private val recipeMapper: RecipeMapper
){

    suspend fun getRecordsFromNetwork(): Flow<ApiCallState> = flow{
        emit(ApiCallState.Loading)
        try {
            val networkRecipes = retrofit.getRecipes()
            val networkCategories = retrofit.getCategories()
            val recipes = recipeMapper.mapFromEntityList(networkRecipes)
            val categories = categoryMapper.mapFromEntityList(networkCategories)
            for(recipe in recipes){
                recipeDao.insert(recipe)
            }
            for (category in categories){
                categoryDao.insert(category)
            }
            emit(ApiCallState.Success)
        }
        catch (e: HttpException){
            emit(ApiCallState.Error(e))
        }
    }


}