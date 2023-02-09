package com.tryden.tobuy.arch

import com.tryden.tobuy.database.AppDatabase
import com.tryden.tobuy.database.entity.ItemEntity

class ToBuyRepository(
    private val appDatabase: AppDatabase
) {

    suspend fun insertItem(itemEntity: ItemEntity) {
        appDatabase.itemEntityDao().insert(itemEntity)
    }

    suspend fun deleteItem(itemEntity: ItemEntity) {
        appDatabase.itemEntityDao().delete(itemEntity)
    }

    suspend fun getAllItems(): List<ItemEntity> {
        return appDatabase.itemEntityDao().getAllItemEntities()
    }
}