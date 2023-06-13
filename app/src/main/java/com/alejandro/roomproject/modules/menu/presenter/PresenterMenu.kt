package com.alejandro.roomproject.modules.menu.presenter

import android.content.Context
import android.content.Intent
import com.alejandro.roomproject.modules.users.registeruser.views.RegisterUsersActivity
import com.alejandro.roomproject.modules.users.informationusers.views.ui.users.InfoUsersActivity
import com.alejandro.roomproject.modules.users.profileuser.views.ProfileUserActivity

class PresenterMenu(var mContext: Context) {

    fun goToRegister() {
        val mIntent = Intent(mContext, RegisterUsersActivity::class.java)
        mContext.startActivity(mIntent)
    }

    fun goToInformation() {
        val mIntent = Intent(mContext, InfoUsersActivity::class.java)
        mContext.startActivity(mIntent)
    }
    fun goToProfile() {
        val mIntent = Intent(mContext, ProfileUserActivity::class.java)
        mContext.startActivity(mIntent)
    }
}