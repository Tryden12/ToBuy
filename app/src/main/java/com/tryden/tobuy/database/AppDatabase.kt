package com.tryden.tobuy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tryden.tobuy.database.dao.CategoryEntityDao
import com.tryden.tobuy.database.dao.ItemEntityDao
import com.tryden.tobuy.database.entity.CategoryEntity
import com.tryden.tobuy.database.entity.ItemEntity


@Database(
    entities = [ItemEntity::class, CategoryEntity::class],
    version = 2
)

abstract class AppDatabase: RoomDatabase() {

    companion object {
        private var appDatabase: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (appDatabase != null) {
                return appDatabase!!
            }

            appDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                name = "to-buy-database"
            )
                .addMigrations(MIGRATION_1_2())
                .build()
            return appDatabase!!
        }
    }

   class MIGRATION_1_2: Migration(1,2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `category_entity` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY(`id`))")
        }
    }

    abstract fun itemEntityDao(): ItemEntityDao
    abstract fun categoryEntityDao(): CategoryEntityDao
}