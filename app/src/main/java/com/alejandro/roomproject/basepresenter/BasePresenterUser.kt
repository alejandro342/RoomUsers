package com.alejandro.roomproject.basepresenter

import android.text.TextUtils

open class BasePresenterUser {


    //validar correo
    fun String.mValidateEmail(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

}





































