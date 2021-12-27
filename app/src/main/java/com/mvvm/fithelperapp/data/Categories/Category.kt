package com.mvvm.fithelperapp.data.Categories

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "categories_table")
data class Category(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val thumbpath: String,
    val description: String

    ) : Parcelable