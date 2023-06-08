package com.alejandro.roomproject.modules.login.presenter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.alejandro.roomproject.R
import com.alejandro.roomproject.basepresenter.BasePresenterUser
import com.alejandro.roomproject.data.entity.AppDatabase
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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PresenterLogin(private var mContext: Context) : InterfaceLogin, BasePresenterUser(),
    CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    //Room
    private val miRoomDB = RoomDataBase.getInstance(mContext.applicationContext)
    private val miBD = miRoomDB.getMiBaseDeDatos()
    private val userDao = miBD.userDao()

    override fun loginUser(email: String, password: String): Boolean {

        if (email.isEmpty()) {
            mContext.myToast(R.string.txt_email_void_register_user)
            return false
        } else if (!email.mValidateEmail()) {

            mContext.myToast(R.string.txt_email_valid_void_register_user)
            return false
        } else if (password.isEmpty()) {
            mContext.myToast(R.string.txt_password_void_register_user)
        } else {
            login(email, password)

        }
        return true
    }

    override fun saveSession(infoSession: String) {
        val sharedPref = mContext.let { SharedPref(it) }
        val gson = Gson()
        val user = gson.fromJson(infoSession, Users::class.java)
        sharedPref.saveSession("user", user)

        loginSuccessful()
    }

    fun login(email: String, password: String) {
        launch(Dispatchers.IO) {
            val user = userDao.loginUser(email, password)
            withContext(Dispatchers.Main) {
                    if (user != null) {
                        saveSession(user.toJson())
                        loginSuccessful()
                    } else {
                        mContext.myToast(R.string.textErrorEmailAndPassword)
                    }
            }
        }
    }

    fun getFromSession() {
        val sharedPref = mContext.let { SharedPref(it) }
        val gson = Gson()

        launch(Dispatchers.IO) {
            if (!sharedPref.getInformation("user").isNullOrBlank()) {
                val mUser = gson.fromJson(sharedPref.getInformation("user"), Users::class.java)
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
        mContext.startActivity(mIntent)
    }

    fun forgotMyDetails() {
        mContext.myToast("olvi")
    }

    private fun loginSuccessful() {
        val mIntent = Intent(mContext, MenuActivity::class.java)
        mIntent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        mContext.startActivity(mIntent)
    }

    private fun login() {
        val mIntent = Intent(mContext, LoginActivity::class.java)
        mContext.startActivity(mIntent)
    }
}