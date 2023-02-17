package com.tryden.tobuy.arch

import com.tryden.tobuy.database.AppDatabase
import com.tryden.tobuy.database.entity.CategoryEntity
import com.tryden.tobuy.database.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

class ToBuyRepository(
    private val appDatabase: AppDatabase
) {

    // region ItemEntity
    suspend fun insertItem(itemEntity: ItemEntity) {
        appDatabase.itemEntityDao().insert(itemEntity)
    }

    suspend fun deleteItem(itemEntity: ItemEntity) {
        appDatabase.itemEntityDao().delete(itemEntity)
    }

    suspend fun updateItem(itemEntity: ItemEntity) {
        appDatabase.itemEntityDao().update(itemEntity)
    }

    fun getAllItems(): Flow<List<ItemEntity>> {
        return appDatabase.itemEntityDao().getAllItemEntities()
    }
    // endregion ItemEntity

    // region CategoryEntity
    suspend fun insertCategory(categoryEntity: CategoryEntity) {
        appDatabase.categoryEntityDao().insert(categoryEntity)
    }

    suspend fun deleteCategory(categoryEntity: CategoryEntity) {
        appDatabase.categoryEntityDao().delete(categoryEntity)
    }

    suspend fun updateCategory(categoryEntity: CategoryEntity) {
        appDatabase.categoryEntityDao().update(categoryEntity)
    }

    fun getAllCategories(): Flow<List<CategoryEntity>> {
        return appDatabase.categoryEntityDao().getAllCategoryEntities()
    }
    // endregion CategoryEntity




}