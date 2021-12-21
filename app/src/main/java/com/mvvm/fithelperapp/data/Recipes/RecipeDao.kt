package com.mvvm.fithelperapp.data.Recipes

import androidx.room.*

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes_table ORDER BY name")

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

    @Update
    suspend fun update(recipe: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)




}