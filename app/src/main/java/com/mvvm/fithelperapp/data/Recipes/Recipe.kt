package com.mvvm.fithelperapp.data.Recipes

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipes_table")
data class Recipe (
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val category: String,
    val country: String,
    val instructions: String,
    val thumbpath: String,
    val tags: String,
    val ingredients: String,
    val measures: String,
    val nutrients: String,
    val cooktime: String,
    val favorite: Boolean = false
    ) : Parcelable
