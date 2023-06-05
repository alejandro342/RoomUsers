package com.alejandro.roomproject.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(

    @PrimaryKey var usuario:String,
    @ColumnInfo(name = "name") var name: String,
    var email: String,
    var password: String,
    var isConnected: Boolean
)