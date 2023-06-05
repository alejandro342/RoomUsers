package com.alejandro.roomproject.data.roomdb

import android.app.Application
import androidx.room.Room
import com.alejandro.roomproject.data.entity.AppDatabase

class RoomDataBase : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my-database"
        ).build()
    }
}