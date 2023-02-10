package com.tryden.tobuy.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tryden.tobuy.database.entity.ItemEntity


@Dao
interface ItemEntityDao {

    @Query("SELECT * FROM item_entity")
    suspend fun getAllItemEntities(): List<ItemEntity>

    @Insert
    suspend fun insert(itemEntity: ItemEntity)

    @Delete
    suspend fun delete(itemEntity: ItemEntity)
}
