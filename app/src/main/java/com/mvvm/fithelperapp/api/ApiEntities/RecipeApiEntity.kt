package com.mvvm.fithelperapp.api.ApiEntities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class RecipeApiEntity(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("category")
    @Expose
    val category: String,

    @SerializedName("country")
    @Expose
    val country: String,

    @SerializedName("instructions")
    @Expose
    val instructions: String,

    @SerializedName("thumbpath")
    @Expose
    val thumbpath: String,

    @SerializedName("tags")
    @Expose
    val tags: String,

    @SerializedName("ingredients")
    @Expose
    val ingredients: String,

    @SerializedName("measures")
    @Expose
    val measures: String,

    @SerializedName("nutrients")
    @Expose
    val nutrients: String,

    @SerializedName("cooktime")
    @Expose
    val cooktime: String

)
