package com.alejandro.roomproject.modules.menu.presenter

import android.content.Context
import android.content.Intent
import com.alejandro.roomproject.modules.registeruser.views.RegisterUsersActivity
import com.alejandro.roomproject.modules.users.views.ui.users.InfoUsersActivity

class PresenterMenu(var mContext: Context) {

    fun goToRegister() {
        val mIntem = Intent(mContext, RegisterUsersActivity::class.java)
        mContext.startActivity(mIntem)
    }

    fun goToInformation() {
        val mIntem = Intent(mContext, InfoUsersActivity::class.java)
        mContext.startActivity(mIntem)
    }
}