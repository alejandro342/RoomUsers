package com.alejandro.roomproject.modules.login.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alejandro.roomproject.databinding.ActivityLoginBinding
import com.alejandro.roomproject.modules.login.presenter.PresenterLogin


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var mBinding: ActivityLoginBinding? = null

    var mPresenterLogin: PresenterLogin? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mBinding!!.root)

        mPresenterLogin = PresenterLogin(this)
        mBinding!!.btnLogin.setOnClickListener(this)
        mBinding!!.registerAccount.setOnClickListener(this)
        mBinding!!.forgotMyDetails.setOnClickListener(this)
        mPresenterLogin?.getFromSession()
    }


    override fun onClick(mItem: View?) {
        when (mItem) {
            mBinding!!.btnLogin -> mPresenterLogin?.loginUser(
                mBinding!!.editEmailLogin.text.toString().trim(),
                mBinding!!.editPasswordLogin.text.toString().trim()
            )

            mBinding!!.registerAccount -> mPresenterLogin?.registerAccount()

            mBinding!!.forgotMyDetails -> mPresenterLogin?.forgotMyDetails()
        }
    }
}