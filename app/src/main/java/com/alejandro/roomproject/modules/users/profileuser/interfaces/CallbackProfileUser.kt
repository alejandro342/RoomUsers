package com.alejandro.roomproject.modules.users.profileuser.interfaces

interface CallbackProfileUser {
    fun setDataUser(name: String, user: String, email: String, status: Boolean, imageUser: String)
}