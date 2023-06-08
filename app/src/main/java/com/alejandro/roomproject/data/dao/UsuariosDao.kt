package com.alejandro.roomproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alejandro.roomproject.models.Users

@Dao
interface UsuariosDao {

    @Query("SELECT * FROM users")
    suspend fun getUsuarios(): MutableList<Users>

    @Insert
    suspend fun registerUser(user: Users)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun loginUser(email: String, password: String): Users?

    @Query("SELECT COUNT(*) FROM users WHERE email = :email OR usuario =:usuario")
     suspend fun verifyEmailAndUser(email: String, usuario:String): Int
}