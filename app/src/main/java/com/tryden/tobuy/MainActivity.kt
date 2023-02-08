package com.tryden.tobuy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tryden.tobuy.db.AppDatabase

class MainActivity : AppCompatActivity() {

    private val db: RoomDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java, name = "to-buy-database"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}