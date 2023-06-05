package com.alejandro.roomproject.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alejandro.roomproject.data.dao.UsuariosDao
import com.alejandro.roomproject.models.Users

@Database(entities = [Users::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UsuariosDao
}