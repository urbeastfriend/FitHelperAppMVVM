package com.mvvm.fithelperapp.data.Categories

import androidx.room.*

import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories_table ORDER BY name")
    fun getCategories() : Flow<List<Category>>

    @Query("SELECT * FROM categories_table")
    fun getCategoriesList() : List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)
}