package com.alejandro.roomproject.modules.users.profileuser.presenter

import android.content.Context
import android.content.Intent
import com.alejandro.roomproject.modules.users.updateduserpassword.views.UpdatedPasswordUserActivity

class PresenterProfileUser(private var mContext: Context) {

    fun gotoEditPassword() {
        val mInten = Intent(mContext, UpdatedPasswordUserActivity::class.java)
        mContext.startActivity(mInten)
    }
}