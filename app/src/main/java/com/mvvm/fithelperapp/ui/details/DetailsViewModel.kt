package com.mvvm.fithelperapp.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mvvm.fithelperapp.data.Recipes.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val state: SavedStateHandle
) : ViewModel(){

    private val recipe = state.get<Recipe>("recipe")
    private val nutrients = recipe?.nutrients?.split(",") ?: List<String>(4){"0 гр"}
    private val inlist: List<String> = recipe?.ingredients?.split(",") ?: emptyList()
    private val melist: List<String> = recipe?.measures?.split(",") ?: emptyList()

    val name = recipe?.name ?: ""
    val category = recipe?.category ?: ""
    val country = recipe?.country ?: ""
    val instuctions = recipe?.instructions ?: ""
    val cooktime = recipe?.cooktime ?: ""
    val thumbpath = recipe?.thumbpath ?: ""
    val favorite = recipe?.favorite ?: false

    val calories = nutrients[0]
    val protein = nutrients[1]
    val fats = nutrients[2]
    val carbs = nutrients[3]
    val ingredients = joinStringWithMarking(inlist)
    val measures = joinStringWithMarking(melist)

    private fun joinStringWithMarking(list: List<String>) : String{
        val marking = "\n \u2022 "
        var res = marking

        for (a in list){
            res+= marking + a
        }

        return res
    }


}