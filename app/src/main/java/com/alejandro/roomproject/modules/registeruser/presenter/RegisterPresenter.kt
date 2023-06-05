package com.alejandro.roomproject.modules.registeruser.presenter

import android.content.Context
import androidx.room.Room
import com.alejandro.roomproject.basepresenter.BasePresenterUser
import com.alejandro.roomproject.data.entity.AppDatabase
import com.alejandro.roomproject.extenciones.myToast
import com.alejandro.roomproject.models.Users
import com.alejandro.roomproject.modules.registeruser.interfaces.InterfaceRegister
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class RegisterPresenter(var mContext: Context) : InterfaceRegister, BasePresenterUser(),
    CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    //Room
    var room: AppDatabase
    lateinit var mUser: Users

    init {
        room = Room.databaseBuilder(mContext, AppDatabase::class.java, "my_database").build()

    }

    override fun registerData(
        user: String,
        name: String,
        email: String,
        password: String,
        repeatPassword: String,
        status: Boolean
    ): Boolean {
        if (user.isEmpty()) {
            mContext.myToast("Campo usuario vacio")
            return false
        } else if (name.isEmpty()) {
            mContext.myToast("Campo nombre vacio")
            return false
        } else if (email.isEmpty()) {
            mContext.myToast("Campo correo vacio")
            return false
        } else if (!email.mValidateEmail()) {
            mContext.myToast("Correo no valido")
            return false
        } else if (password.isEmpty()) {
            mContext.myToast("Campo contraseña vacio")
            return false
        } else if (repeatPassword.isEmpty()) {
            mContext.myToast("Campo repetir contraseña vacio")
            return false
        } else if (password.equals(repeatPassword)) {

            mUser = Users(user, name, email, password, true)
            addUser(room, mUser)
            mContext.myToast("Usuario creado correctamente")

        } else {
            mContext.myToast("las contraseñas no son iguales")
        }
        return true
    }

    private fun addUser(room: AppDatabase, user: Users) {
        launch(Dispatchers.IO) {
            room.userDao().registerUser(user)
        }
    }

}
