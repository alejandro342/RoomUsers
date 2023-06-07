package com.alejandro.roomproject.data.roomdb


import android.content.Context
import androidx.room.Room
import com.alejandro.roomproject.data.entity.AppDatabase

class RoomDataBase private constructor(context: Context) {

    private val miBaseDeDatos: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "my_database"
    ).build()

    companion object {
        @Volatile
        private var INSTANCE: RoomDataBase? = null

        fun getInstance(context: Context): RoomDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RoomDataBase(context).also { INSTANCE = it }
            }
    }

    fun getMiBaseDeDatos(): AppDatabase {
        return miBaseDeDatos
    }
}