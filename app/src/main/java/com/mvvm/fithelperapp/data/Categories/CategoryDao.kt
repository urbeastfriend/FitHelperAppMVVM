package com.mvvm.fithelperapp.data.Categories

import androidx.room.*


@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories_table ORDER BY name")

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)
}