package com.alejandro.roomproject.modules.getdatauser.presenter

import android.content.Context
import android.util.Log
import com.alejandro.roomproject.basepresenter.BasePresenterUser
import com.alejandro.roomproject.extenciones.myToast
import com.alejandro.roomproject.modules.getdatauser.interfaces.InterfaceGetDataUser
import com.alejandro.roomproject.modules.getdatauser.interfaces.InterfaceShowPassword
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PresenterGetDataUser(mContext: Context, showPassword: InterfaceShowPassword) :
    BasePresenterUser(mContext), CoroutineScope,
    InterfaceGetDataUser {
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    var viewPassword: InterfaceGetDataUser.ViewPassword? = null
    var showPassword: InterfaceShowPassword? = null

    init {
        this.showPassword = showPassword
    }

    override fun getPassword(usuario: String): Boolean {
        if (usuario.isEmpty()) {
            mContext?.myToast("Campo usuario vacio")
            return false
        } else {
            getPasswordU(usuario)
        }
        return true
    }

    private fun getPasswordU(usuario: String) {
        launch(Dispatchers.IO) {
            val password = miBD.userDao().getPassword(usuario)
            if (password != null) {
                Log.d("TAGPassword", password)
                viewPassword?.viewPassword(password)
                withContext(Dispatchers.Main) {
                    showPassword?.showPassword()
                }
            } else {
                withContext(Dispatchers.Main) {
                    mContext?.myToast("usuario no encontrado")
                    showPassword?.hidePassword()
                }
            }
        }
    }

    fun attachView(viewPassword: InterfaceGetDataUser.ViewPassword) {
        this.viewPassword = viewPassword
    }

    fun detachView() {
        this.viewPassword = null
    }
//TAGPassword AndroidRuntime
}