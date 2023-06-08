package com.alejandro.roomproject.data.roomdb


import android.content.Context
import androidx.room.Room
import com.alejandro.roomproject.data.entity.AppDatabase

class RoomDataBase private constructor(context: Context) {

    private val myBD: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "my_database"
    ).build()

    companion object {
        @Volatile
        private var ROOMDB: RoomDataBase? = null

        fun getInstance(context: Context): RoomDataBase =
            ROOMDB ?: synchronized(this) {
                ROOMDB ?: RoomDataBase(context).also { ROOMDB = it }
            }
    }

    fun getMiBaseDeDatos(): AppDatabase {
        return myBD
    }
}