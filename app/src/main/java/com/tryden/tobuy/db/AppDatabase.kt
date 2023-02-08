package com.tryden.tobuy.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tryden.tobuy.dao.ItemEntityDao
import com.tryden.tobuy.model.ItemEntity


@Database(entities = [ItemEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun itemEntityDao(): ItemEntityDao
}