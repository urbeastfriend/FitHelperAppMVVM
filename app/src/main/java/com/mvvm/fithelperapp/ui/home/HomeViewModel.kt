package com.mvvm.fithelperapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mvvm.fithelperapp.data.Categories.Category
import com.mvvm.fithelperapp.data.Categories.CategoryDao
import com.mvvm.fithelperapp.data.Recipes.Recipe
import com.mvvm.fithelperapp.data.Recipes.RecipeDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryDao: CategoryDao,
    private val recipeDao: RecipeDao
) : ViewModel(){

    private val homeEventChannel = Channel<HomeEvent>()
    val homeEvent = homeEventChannel.receiveAsFlow()

    val categories = categoryDao.getCategories().asLiveData()
    val recipes = recipeDao.getRecipes().asLiveData()



    fun onCategorySelected(categoryClickedPosition: Int){
        viewModelScope.launch {
            homeEventChannel.send(HomeEvent.NavigateToCategoriesScreen(categoryClickedPosition))
        }
    }

    fun onRecipeSelected(recipe: Recipe){
        viewModelScope.launch {
            homeEventChannel.send(HomeEvent.NavigateToRecipeScreen(recipe))
        }
    }




    sealed class HomeEvent{

        data class NavigateToCategoriesScreen(val categoryClickedPosition: Int) : HomeEvent()

        data class NavigateToRecipeScreen(val recipe: Recipe) : HomeEvent()
    }
}