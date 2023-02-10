package com.tryden.tobuy.database.dao

import androidx.room.*
import com.tryden.tobuy.database.entity.ItemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemEntityDao {

    @Query("SELECT * FROM item_entity")
    fun getAllItemEntities(): Flow<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itemEntity: ItemEntity)

    @Delete
    suspend fun delete(itemEntity: ItemEntity)
}
