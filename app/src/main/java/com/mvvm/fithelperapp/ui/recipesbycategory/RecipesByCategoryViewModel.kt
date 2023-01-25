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
    private val recipeDao: RecipeDao,
    private val savedState: SavedStateHandle
) : ViewModel() {

    val categories = categoryDao.getCategories().asLiveData()

    private val currentCategory = savedState.getLiveData("category","")


    private val currentRecipesFlow = currentCategory.asFlow().flatMapLatest { recipeDao.getRecipesByCategory(it) }
    val currentRecipes = currentRecipesFlow.asLiveData()


    fun onCurrentCategoryChanged(categoryName: String){
        viewModelScope.launch {
            currentCategory.value = categoryName
        }
    }
}