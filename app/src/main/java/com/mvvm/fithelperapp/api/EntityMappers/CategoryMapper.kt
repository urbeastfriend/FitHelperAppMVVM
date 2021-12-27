package com.mvvm.fithelperapp.api.EntityMappers

import com.mvvm.fithelperapp.api.ApiEntities.CategoryApiEntity
import com.mvvm.fithelperapp.data.Categories.Category
import com.mvvm.fithelperapp.util.EntityMapper
import javax.inject.Inject

class CategoryMapper @Inject constructor() : EntityMapper<CategoryApiEntity,Category> {

    override fun mapFromEntity(entity: CategoryApiEntity): Category {
        return Category(
            id = entity.id,
            name = entity.name,
            thumbpath = entity.thumbpath,
            description = entity.description
        )
    }

    override fun mapToEntity(domainModel: Category): CategoryApiEntity {
        return CategoryApiEntity(
            id = domainModel.id,
            name = domainModel.name,
            thumbpath = domainModel.thumbpath,
            description = domainModel.description
        )
    }

    override fun mapFromEntityList(entities: List<CategoryApiEntity>): List<Category> {
        return entities.map { mapFromEntity(it) }
    }
}