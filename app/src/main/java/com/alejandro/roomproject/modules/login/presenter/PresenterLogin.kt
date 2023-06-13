package com.alejandro.roomproject.modules.login.presenter

import android.content.Context
import android.content.Intent
import com.alejandro.roomproject.R
import com.alejandro.roomproject.basepresenter.BasePresenterUser
import com.alejandro.roomproject.data.roomdb.RoomDataBase
import com.alejandro.roomproject.extenciones.myToast
import com.alejandro.roomproject.models.Users
import com.alejandro.roomproject.modules.login.interfaces.InterfaceLogin
import com.alejandro.roomproject.modules.login.views.LoginActivity
import com.alejandro.roomproject.modules.menu.view.MenuActivity
import com.alejandro.roomproject.modules.users.registeruser.views.RegisterUsersActivity
import com.alejandro.roomproject.utils.SharedPref
import com.google.gson.Gson

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PresenterLogin(mContext: Context) : InterfaceLogin, BasePresenterUser(mContext),
    CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    override fun loginUser(email: String, password: String): Boolean {

        if (email.isEmpty()) {
            mContext?.myToast(R.string.txt_email_void_register_user)
            return false
        } else if (!email.mValidateEmail()) {

            mContext?.myToast(R.string.txt_email_valid_void_register_user)
            return false
        } else if (password.isEmpty()) {
            mContext?.myToast(R.string.txt_password_void_register_user)
        } else {
            login(email, password)
        }
        return true
    }

    fun login(email: String, password: String) {
        launch(Dispatchers.IO) {
            val user = userDao.loginUser(email, password)
            if (user != null) {
                setStatus(user)
                saveSession(user.toJson())
                loginSuccessful()
            } else {
                withContext(Dispatchers.Main) {
                    mContext?.myToast(R.string.textErrorEmailAndPassword)
                }
            }
        }

    }

    fun getFromSession() {

        launch(Dispatchers.IO) {
            if (!sharedPref?.getInformation("user").isNullOrBlank()) {
                if (mUser != null) {
                    loginSuccessful()
                } else {
                    login()
                }
            }
        }
    }

    fun registerAccount() {
        val mIntent = Intent(mContext, RegisterUsersActivity::class.java)
        mContext?.startActivity(mIntent)
    }

    fun forgotMyDetails() {
        mContext?.myToast("olvi")
    }

    private fun loginSuccessful() {
        val mIntent = Intent(mContext, MenuActivity::class.java)
        mIntent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        mContext?.startActivity(mIntent)
    }

    private fun login() {
        val mIntent = Intent(mContext, LoginActivity::class.java)
        mContext?.startActivity(mIntent)
    }

    private fun setStatus(user: Users) {
        updateStatus(user.usuario, true)
    }

    private fun updateStatus(usuario: String, isConnected: Boolean) {
        launch {
            userDao.updateStatus(usuario, isConnected)
        }
    }
}