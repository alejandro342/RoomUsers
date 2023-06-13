package com.alejandro.roomproject.modules.users.registeruser.interfaces

interface InterfaceRegister {
    fun registerData(
        user: String,
        name: String,
        email: String,
        password: String,
        repeatPassword: String,
        status: Boolean,
        imageUser:String
    ): Boolean

    interface dialog {
        fun showDialog()
    }
}