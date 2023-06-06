package com.alejandro.roomproject.modules.login.presenter

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.room.Room
import com.alejandro.roomproject.R
import com.alejandro.roomproject.basepresenter.BasePresenterUser
import com.alejandro.roomproject.data.entity.AppDatabase
import com.alejandro.roomproject.extenciones.myToast
import com.alejandro.roomproject.modules.login.interfaces.InterfaceLogin
import com.alejandro.roomproject.modules.menu.view.MenuActivity
import com.alejandro.roomproject.modules.registeruser.views.RegisterUsersActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PresenterLogin(var mContext: Context) : InterfaceLogin, BasePresenterUser(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    //Room
    var room: AppDatabase

    init {
        room = Room.databaseBuilder(mContext, AppDatabase::class.java, "my_database").build()

    }

    val userDao = room.userDao()

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

    fun login(email: String, password: String) {
        launch(Dispatchers.IO) {
            val user = userDao.loginUser(email, password)
            withContext(Dispatchers.Main) {
                if (user != null) {
                    loginSuccessful()
                } else {
                    Toast.makeText(
                        mContext,
                        "El correo o la contraseña esta mal",
                        Toast.LENGTH_SHORT
                    ).show()
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
        mContext.startActivity(mIntent)
    }
}