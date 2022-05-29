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

    private val _state = MutableLiveData<RecipesByCategoryViewState>()
    val state: LiveData<RecipesByCategoryViewState> get() = _state

    private val _currentCategory = MutableLiveData<Category>()
    val currentCategory: LiveData<Category> get() = _currentCategory

    private val _allCategories = MutableLiveData<Flow<List<Category>>>()
    val allCategories: LiveData<Flow<List<Category>>> get() = _allCategories






    init {
        viewModelScope.launch {

            val categories = categoryDao.getCategories()
            _allCategories.value = categories
            val currentCategory = savedState.get<Category>("category")
            _currentCategory.value = currentCategory!!

        }
    }

    data class RecipesByCategoryViewState(
        val currentCategory: Category,
        val recipesByCategory: Flow<List<Recipe>>
        )

    private fun getRecipesByCategory(categoryname: String) {

    }

    private suspend fun getCategoriesListAsync(): List<Category> = withContext(Dispatchers.IO) {
        categoryDao.getCategoriesList()
    }
}