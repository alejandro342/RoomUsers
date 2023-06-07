package com.alejandro.roomproject.modules.users.profileuser.presenter

import android.content.Context
import android.content.Intent
import com.alejandro.roomproject.models.Users
import com.alejandro.roomproject.modules.login.views.LoginActivity
import com.alejandro.roomproject.modules.users.profileuser.interfaces.InterfaceProfileUser
import com.alejandro.roomproject.modules.users.updateduserpassword.views.UpdatedPasswordUserActivity
import com.alejandro.roomproject.utils.SharedPref
import com.google.gson.Gson

class PresenterProfileUser(mContext: Context, private val viewData: InterfaceProfileUser) {
    private var mContext: Context? = null
    private var sharedPref: SharedPref? = null
    private var mUser: Users? = null

    init {
        this.mContext = mContext
        sharedPref = SharedPref(mContext)
    }

    fun gotoEditPassword() {
        val mIntent = Intent(mContext, UpdatedPasswordUserActivity::class.java)
        mContext?.startActivity(mIntent)
    }

    fun setDataUser() {
        val name = mUser?.name.toString()
        val user = mUser?.usuario.toString()
        val email = mUser?.email.toString()
        viewData.setDataUser(name, user, email)
    }

    fun getUserFromSession() {
        val gson = Gson()
        if (!sharedPref?.getInformation("user").isNullOrBlank()) {
            mUser = gson.fromJson(sharedPref?.getInformation("user"), Users::class.java)

        }
    }

    fun closeSession(){
        val sharedPref = mContext?.let { SharedPref(it) }
        sharedPref?.closeSession("user")
        val mIntent = Intent(mContext, LoginActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        mContext?.startActivity(mIntent)
    }
}