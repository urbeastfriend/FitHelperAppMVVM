package com.mvvm.fithelperapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mvvm.fithelperapp.data.Categories.Category
import com.mvvm.fithelperapp.data.Categories.CategoryDao
import com.mvvm.fithelperapp.data.Recipes.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryDao: CategoryDao
) : ViewModel(){

    private val homeEventChannel = Channel<HomeEvent>()
    val homeEvent = homeEventChannel.receiveAsFlow()

    val categories = categoryDao.getCategories().asLiveData()




    fun onCategorySelected(category: Category){
        viewModelScope.launch {
            homeEventChannel.send(HomeEvent.NavigateToCategoriesScreen)
        }
    }




    sealed class HomeEvent{

        object NavigateToCategoriesScreen : HomeEvent()

        data class NavigateToRecipeScreen(val recipe: Recipe) : HomeEvent()
    }
}