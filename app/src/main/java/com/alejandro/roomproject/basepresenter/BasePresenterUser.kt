package com.alejandro.roomproject.basepresenter

import android.content.Context
import android.text.TextUtils
import com.alejandro.roomproject.data.roomdb.RoomDataBase
import com.alejandro.roomproject.models.Users
import com.alejandro.roomproject.utils.SharedPref
import com.google.gson.Gson

open class BasePresenterUser(mContext: Context) {

    var mContext: Context? = null
    var sharedPref: SharedPref? = null
    var mUser: Users? = null
    var gson: Gson? = null

    //Room
    private val miRoomDB = RoomDataBase.getInstance(mContext.applicationContext)
    val miBD = miRoomDB.getMiBaseDeDatos()
    val userDao = miBD.userDao()

    init {
        this.mContext = mContext
        sharedPref = SharedPref(mContext)
        gson = Gson()
        mUser = gson?.fromJson(sharedPref?.getInformation("user"), Users::class.java)
    }

    //validar correo
    fun String.mValidateEmail(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

    fun getUserFromSession() {
        val gson = Gson()
        if (!sharedPref?.getInformation("user").isNullOrBlank()) {
            mUser = gson.fromJson(sharedPref?.getInformation("user"), Users::class.java)
        }
    }

    fun saveSession(infoSession: String) {

        val user = gson?.fromJson(infoSession, Users::class.java)
        sharedPref?.saveSession("user", user!!)
    }
}





































