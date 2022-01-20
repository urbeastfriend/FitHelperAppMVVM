package com.mvvm.fithelperapp.ui.recipesbycategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mvvm.fithelperapp.data.Categories.CategoryDao
import com.mvvm.fithelperapp.data.Recipes.RecipeDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesByCategoryViewModel @Inject constructor(
    private val categoryDao: CategoryDao,
    private val recipeDao: RecipeDao
        ) : ViewModel(){



    val categories = categoryDao.getCategories().asLiveData()
    val recipes = recipeDao.getRecipes().asLiveData()
}