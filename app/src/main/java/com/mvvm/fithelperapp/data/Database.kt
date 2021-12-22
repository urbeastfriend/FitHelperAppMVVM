package com.mvvm.fithelperapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mvvm.fithelperapp.data.Categories.Category
import com.mvvm.fithelperapp.data.Categories.CategoryDao
import com.mvvm.fithelperapp.data.Recipes.Recipe
import com.mvvm.fithelperapp.data.Recipes.RecipeDao
import com.mvvm.fithelperapp.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Recipe::class,Category::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase(){

    abstract fun recipeDao(): RecipeDao

    abstract fun categoriesDao(): CategoryDao


    class CallBack @Inject constructor(
        private val database: Provider<com.mvvm.fithelperapp.data.Database>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val recipedao = database.get().recipeDao()
            val categoriesDao = database.get().categoriesDao()

            applicationScope.launch {
                recipedao.insert(Recipe(name = "Свинина на гриле по-португальски", category = "Мясо",
                country = "Португалия", instructions = "Все очень просто, берешь гидравлический фазоинвертор, " +
                            "подключаешь его к циклическому прото-излучателю, потом врубаешь на полную катушку и, вуаля, свинина готова",
                thumbpath = "source/images/vtqxtu1511784197.jpg", tags = "Мясо,Португалия,Свинина,Гриль",
                ingredients = "Свиное филе,Белое вино,Паприка,Лимон,Оливковое масло,Картофель,Майонез",
                measures = "2 шт,200 мл,Пол ст ложки,2 шт,70 грамм,1 килограмм,20 грамм",
                nutrients = "809,50,48,44", cooktime = "50 мин"))

                categoriesDao.insert(Category(name = "Мясо", thumbpath = "source/images/1.jpg",
                    description = "Блюда из мяса занимают особое место в рационе современного человека. " +
                            "В состав мяса входят минеральные вещества и белки, содержащие незаменимые аминокислоты."))
            }
        }
    }
}