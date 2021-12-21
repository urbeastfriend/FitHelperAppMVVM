package com.mvvm.fithelperapp.data.Categories

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "categories_table")
class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val thumbpath: String,
    val description: String

    ) : Parcelable {
}