package com.mvvm.fithelperapp.api.ApiEntities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ApiResponse(
    @SerializedName("categories")
    @Expose
    val categories: List<CategoryApiEntity>,

    @SerializedName("meals")
    @Expose
    val recipes: List<RecipeApiEntity>
)
