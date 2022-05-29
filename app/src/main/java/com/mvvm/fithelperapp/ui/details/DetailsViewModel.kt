package com.mvvm.fithelperapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mvvm.fithelperapp.data.Recipes.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedState: SavedStateHandle
) : ViewModel(){

    private val _state = MutableLiveData<DetailsViewState>()
    val state: LiveData<DetailsViewState> get() = _state

    init {
        val recipe = savedState.get<Recipe>("recipe")
        val nutrients = recipe?.nutrients?.split(",") ?: List<String>(4){"0 гр"}
        val inlist: List<String> = recipe?.ingredients?.split(",") ?: emptyList()
        val melist: List<String> = recipe?.measures?.split(",") ?: emptyList()

        _state.value = DetailsViewState(
            name = recipe?.name ?: "",
            category = recipe?.category ?: "",
            country = recipe?.country ?: "",
            instructions = recipe?.instructions ?: "",
            cooktime = recipe?.cooktime ?: "",
            thumbpath = recipe?.thumbpath ?: "",
            favorite = recipe?.favorite ?: false,
            calories = nutrients[0],
            protein = nutrients[1],
            fats = nutrients[2],
            carbs = nutrients[3],
            ingredients = joinStringWithMarking(inlist),
            measures = joinStringWithMarking(melist)
        )
    }


    private fun joinStringWithMarking(list: List<String>) : String{
        val marking = "\n \u2022 "
        var res = ""

        for (a in list){
            res+= marking + a
        }
        return res
    }
}

data class DetailsViewState (
    val name: String = "",
    val category: String = "",
    val country: String = "",
    val instructions: String = "",
    val cooktime: String = "",
    val thumbpath: String = "",
    val favorite: Boolean = false,
    val calories: String = "",
    val protein: String = "",
    val fats: String = "",
    val carbs: String = "",
    val ingredients: String = "",
    val measures: String = ""
)