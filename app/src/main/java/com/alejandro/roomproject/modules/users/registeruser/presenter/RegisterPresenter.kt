package com.alejandro.roomproject.modules.users.registeruser.presenter

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.alejandro.roomproject.basepresenter.BasePresenterUser
import com.alejandro.roomproject.data.entity.AppDatabase
import com.alejandro.roomproject.data.roomdb.RoomDataBase
import com.alejandro.roomproject.extenciones.myToast
import com.alejandro.roomproject.models.Users
import com.alejandro.roomproject.modules.users.registeruser.interfaces.InterfaceRegister
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class RegisterPresenter(mContext: Context, var mDialog: InterfaceRegister.dialog) :
    InterfaceRegister, BasePresenterUser(mContext),
    CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun registerData(
        user: String,
        name: String,
        email: String,
        password: String,
        repeatPassword: String,
        status: Boolean,
        imageUser: String
    ): Boolean {
        if (user.isEmpty()) {
            mContext?.myToast("Campo usuario vacio")
            return false
        } else if (name.isEmpty()) {
            mContext?.myToast("Campo nombre vacio")
            return false
        } else if (email.isEmpty()) {
            mContext?.myToast("Campo correo vacio")
            return false
        } else if (!email.mValidateEmail()) {
            mContext?.myToast("Correo no valido")
            return false
        } else if (password.isEmpty()) {
            mContext?.myToast("Campo contraseña vacio")
            return false
        } else if (repeatPassword.isEmpty()) {
            mContext?.myToast("Campo repetir contraseña vacio")
            return false
        } else if (password == repeatPassword) {
            if (imageUser.isEmpty() or imageUser.isNotEmpty()) {
                mUser = Users(user, name, email, password, true, imageUser)
                verifyEmailAndUser(miBD, mUser!!)
            }

        } else {
            mContext?.myToast("las contraseñas no son iguales")
        }
        return true
    }

    private fun addUser(room: AppDatabase, user: Users) {
        launch(Dispatchers.IO) {
            room.userDao().registerUser(user)
        }
    }

    private fun verifyEmailAndUser(room: AppDatabase, user: Users) {
        val emailD = mUser!!.email
        val usuario = mUser!!.usuario
        launch(Dispatchers.IO) {
            val mQuantityRegister = room.userDao().verifyEmailAndUser(emailD, usuario)
            if (mQuantityRegister > 0) {
                withContext(Dispatchers.Main) {
                    mContext?.myToast("El correo o usuario ya estan registrados")
                }
            } else {
                addUser(room, user)
                withContext(Dispatchers.Main) {
                    mDialog.showDialog()
                }
            }
        }
    }
}