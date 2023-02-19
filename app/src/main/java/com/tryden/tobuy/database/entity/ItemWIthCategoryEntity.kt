package com.tryden.tobuy.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ItemWIthCategoryEntity(
    @Embedded
    val itemEntity: ItemEntity,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val categoryEntity: CategoryEntity?
) {
}