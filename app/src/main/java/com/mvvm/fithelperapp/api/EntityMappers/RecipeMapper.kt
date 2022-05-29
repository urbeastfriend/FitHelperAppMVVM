package com.mvvm.fithelperapp.api.EntityMappers

import com.mvvm.fithelperapp.api.ApiEntities.RecipeApiEntity
import com.mvvm.fithelperapp.data.Recipes.Recipe
import com.mvvm.fithelperapp.util.EntityMapper
import javax.inject.Inject


/*
   Класс для приведения модели данных, полученной посредством api запроса к другому виду,
   в дальнейшем использующуюся в рамках приложения.
 */
class RecipeMapper @Inject constructor() : EntityMapper<RecipeApiEntity,Recipe>{
    override fun mapFromEntity(entity: RecipeApiEntity): Recipe {
        return Recipe(
            id = entity.id,
            name = entity.name,
            category = entity.category,
            country = entity.country,
            instructions = entity.instructions,
            thumbpath = entity.thumbpath,
            tags = entity.tags,
            ingredients = entity.ingredients,
            measures = entity.measures,
            nutrients = entity.nutrients,
            cooktime = entity.cooktime
        )
    }

    override fun mapToEntity(domainModel: Recipe): RecipeApiEntity {
        return RecipeApiEntity(
            id = domainModel.id,
            name = domainModel.name,
            category = domainModel.category,
            country = domainModel.country,
            instructions = domainModel.instructions,
            thumbpath = domainModel.thumbpath,
            tags = domainModel.tags,
            ingredients = domainModel.ingredients,
            measures = domainModel.measures,
            nutrients = domainModel.nutrients,
            cooktime = domainModel.cooktime
        )
    }

    override fun mapFromEntityList(entities: List<RecipeApiEntity>): List<Recipe>{
        return entities.map { mapFromEntity(it) }
    }
}