package com.alejandro.roomproject.modules.users.updateduserpassword.intefaces

interface InterfaceChangePassword {
    fun changePassword(password: String, repeatPassword: String, oldPassword: String): Boolean

    interface MyDialog {
        fun showDialog()
    }
}