package com.mvvm.fithelperapp.ui.recipesbycategory

import android.util.Log
import androidx.lifecycle.*
import com.mvvm.fithelperapp.data.Categories.Category
import com.mvvm.fithelperapp.data.Categories.CategoryDao
import com.mvvm.fithelperapp.data.Recipes.Recipe
import com.mvvm.fithelperapp.data.Recipes.RecipeDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipesByCategoryViewModel @Inject constructor(
    private val categoryDao: CategoryDao,
    private val savedState: SavedStateHandle
) : ViewModel() {

    val currentCategory = savedState.get<Category>("category")


    val categories = categoryDao.getCategories().asLiveData()


}