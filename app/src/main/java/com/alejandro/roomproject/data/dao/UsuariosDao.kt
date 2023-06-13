package com.alejandro.roomproject.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alejandro.roomproject.models.Users

@Dao
interface UsuariosDao {

    @Query("SELECT * FROM users")
    suspend fun getUsers(): MutableList<Users>

    @Insert
    suspend fun registerUser(user: Users)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun loginUser(email: String, password: String): Users?

    @Query("SELECT COUNT(*) FROM users WHERE email = :email OR usuario =:usuario")
    suspend fun verifyEmailAndUser(email: String, usuario: String): Int

    @Query("UPDATE users SET isConnected = :isConnected WHERE usuario= :usuario")
    suspend fun updateStatus(usuario: String, isConnected: Boolean)

    @Query("UPDATE users SET password = :password WHERE usuario= :usuario")
    suspend fun updatePassword(usuario: String, password: String)

    @Query("UPDATE users SET imageUser = :imageUser WHERE usuario= :usuario")
    suspend fun setImageUser(usuario: String, imageUser: String)

}