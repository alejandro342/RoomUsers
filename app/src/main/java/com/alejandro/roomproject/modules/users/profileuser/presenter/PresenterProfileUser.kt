package com.alejandro.roomproject.modules.users.profileuser.presenter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.alejandro.roomproject.basepresenter.BasePresenterUser
import com.alejandro.roomproject.extenciones.myToast
import com.alejandro.roomproject.modules.login.views.LoginActivity
import com.alejandro.roomproject.modules.users.profileuser.interfaces.CallbackProfileUser
import com.alejandro.roomproject.modules.users.updateduserpassword.views.UpdatedPasswordUserActivity
import com.alejandro.roomproject.utils.SharedPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PresenterProfileUser(mContext: Context) :
    CoroutineScope, BasePresenterUser(mContext) {

    override val coroutineContext: CoroutineContext = Dispatchers.IO
    private var mCallbackProfile: CallbackProfileUser? = null

    fun setDataCallback(mCallbackProfile: CallbackProfileUser) {
        this.mCallbackProfile = mCallbackProfile
    }

    fun upDateStatus(usuario: String, isConnected: Boolean) {
        launch {
            userDao.updateStatus(usuario, isConnected)
        }
    }

    fun deleteUser() {
        launch {
            try {
                miBD.userDao().deleteUser(mUser!!)
            } catch (e: Exception) {
                Log.d("ErrorDe", "${e.message}")
            }
        }
    }

    fun gotoEditPassword() {
        val mIntent = Intent(mContext, UpdatedPasswordUserActivity::class.java)
        mContext?.startActivity(mIntent)
    }

    fun setDataUser() {
        val name = mUser?.name
        val user = mUser?.usuario
        val email = mUser?.email
        val status = mUser?.isConnected
        val imageUser = mUser?.imageUser

        if (name != null) {
            if (user != null) {
                if (email != null) {
                    if (status != null) {
                        if (imageUser != null) {
                            mCallbackProfile?.setDataUser(name, user, email, status, imageUser)
                        }
                    }
                }
            }
        }
    }

    fun closeSession() {
        val sharedPref = mContext?.let { SharedPref(it) }
        sharedPref?.closeSession("user")
        val mIntent = Intent(mContext, LoginActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        mContext?.startActivity(mIntent)
    }


    fun processImage(imageUri: Uri) {
        launch {
            val uri: Uri = imageUri
            val uriString: String = uri.toString()
            try {
                userDao.setImageUser(mUser!!.usuario, uriString)
                withContext(Dispatchers.Main) { mContext?.myToast("Imagen guardada") }
            } catch (e: Exception) {
                Log.d("Error", "${e.message}")
            }
        }
    }

}