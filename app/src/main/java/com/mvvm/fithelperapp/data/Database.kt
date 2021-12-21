package com.mvvm.fithelperapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mvvm.fithelperapp.data.Recipes.Recipe
import com.mvvm.fithelperapp.data.Recipes.RecipeDao
import com.mvvm.fithelperapp.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase(){

    abstract fun recipeDao(): RecipeDao


    class CallBack @Inject constructor(
        private val database: Provider<com.mvvm.fithelperapp.data.Database>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().recipeDao()

            applicationScope.launch {

            }
        }
    }
}