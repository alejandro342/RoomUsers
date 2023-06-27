package com.alejandro.roomproject.modules.getdatauser.interfaces

interface InterfaceGetDataUser {

    interface ViewPassword{
        fun viewPassword(password:String)
    }

    fun getPassword(usuario: String):Boolean
}