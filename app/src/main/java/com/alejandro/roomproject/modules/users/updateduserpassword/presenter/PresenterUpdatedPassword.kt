package com.alejandro.roomproject.modules.users.updateduserpassword.presenter

import android.content.Context
import com.alejandro.roomproject.R
import com.alejandro.roomproject.basepresenter.BasePresenterUser
import com.alejandro.roomproject.extenciones.myToast
import com.alejandro.roomproject.modules.users.updateduserpassword.intefaces.CallbackUserUpdatedPassword
import com.alejandro.roomproject.modules.users.updateduserpassword.intefaces.InterfaceChangePassword
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PresenterUpdatedPassword(mContext: Context, var mDialog: InterfaceChangePassword.MyDialog) :
    CoroutineScope,
    BasePresenterUser(mContext), InterfaceChangePassword {

    override val coroutineContext: CoroutineContext = Dispatchers.IO
    private var mCallbackUser: CallbackUserUpdatedPassword? = null


    fun setUserCallback(mCallbackUser: CallbackUserUpdatedPassword) {
        this.mCallbackUser = mCallbackUser
    }

    fun setUser() {
        val user = mUser?.usuario
        if (user != null) {
            mCallbackUser?.user(user)
        }
    }

    override fun changePassword(
        password: String,
        repeatPassword: String,
        oldPassword: String
    ): Boolean {
        if (password.isEmpty()) {
            mContext?.myToast(R.string.txt_password_void_register_user)
            return false
        } else if (repeatPassword.isEmpty()) {
            mContext?.myToast(R.string.txt_repeat_password_void_register_user)
            return false
        } else if (password.equals(repeatPassword)) {
            if (oldPassword.equals(mUser!!.password)) {
                updatePassword(mUser!!.usuario, password)
                mDialog.showDialog()
            } else {
                mContext?.myToast("la contraseña indicada no es la misma con su contraseña antigua")
            }
        } else {
            mContext?.myToast("las contraseñas no son iguales")
        }
        return true
    }

    fun updatePassword(usuario: String, password: String) {
        launch {
            userDao.updatePassword(usuario, password)
        }
    }
}