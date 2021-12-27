package com.mvvm.fithelperapp.ui.mainactivity

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
import java.lang.Exception

import javax.inject.Inject

class MainRepository @Inject constructor(
    private val retrofit: FitHelperApi,
    private val recipeDao: RecipeDao,
    private val categoryDao: CategoryDao,
    private val categoryMapper: CategoryMapper,
    private val recipeMapper: RecipeMapper
){

    suspend fun getRecipes(): Flow<ApiCallState<Nothing>> = flow{
        emit(ApiCallState.Loading)
        try {
            val networkRecipes = retrofit.getRecipes()
            val recipes = recipeMapper.mapFromEntityList(networkRecipes)
            for(recipe in recipes){
                recipeDao.insert(recipe)
            }
        }
        catch (e: HttpException){
            emit(ApiCallState.Error(e))
        }
    }
}