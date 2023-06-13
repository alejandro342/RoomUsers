package com.alejandro.roomproject.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "users")
data class Users(

    @PrimaryKey var usuario: String,
    @ColumnInfo(name = "name") var name: String,
    var email: String,
    var password: String,
    var isConnected: Boolean,
    var imageUser: String
) {
    override fun toString(): String {
        return "Users(usuario='$usuario', name='$name', email='$email', password='$password', isConnected=$isConnected)"
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }

}