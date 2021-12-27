package com.mvvm.fithelperapp.api.ApiEntities

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryApiEntity(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("thumbpath")
    @Expose
    val thumbpath: String,

    @SerializedName("description")
    @Expose
    val description: String,
)