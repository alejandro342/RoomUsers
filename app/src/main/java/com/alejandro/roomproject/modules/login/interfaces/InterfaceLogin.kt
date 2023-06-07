package com.alejandro.roomproject.modules.login.interfaces

interface InterfaceLogin {
    fun loginUser(email:String,password:String):Boolean
    fun saveSession(infoSession:String)
}