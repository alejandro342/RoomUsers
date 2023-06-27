package com.alejandro.roomproject.modules.getdatauser.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alejandro.roomproject.databinding.ActivityGetDataUserBinding
import com.alejandro.roomproject.modules.getdatauser.interfaces.InterfaceGetDataUser
import com.alejandro.roomproject.modules.getdatauser.interfaces.InterfaceShowPassword
import com.alejandro.roomproject.modules.getdatauser.presenter.PresenterGetDataUser

class GetDataUserActivity : AppCompatActivity(), View.OnClickListener,
    InterfaceGetDataUser.ViewPassword, InterfaceShowPassword {

    private var mBinding: ActivityGetDataUserBinding? = null
    private var mPresenterGetData: PresenterGetDataUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGetDataUserBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        mPresenterGetData = PresenterGetDataUser(this, this)
        mPresenterGetData?.attachView(this)
        mBinding!!.btnGetPassword.setOnClickListener(this)
    }

    override fun onClick(mItem: View?) {
        when (mItem) {
            mBinding!!.btnGetPassword -> {
                mPresenterGetData?.getPassword(mBinding!!.editUserOrEmail.text.toString())
            }
        }
    }

    override fun viewPassword(password: String) {
        mBinding!!.textViewPasswordUser.text = password
    }

    override fun showPassword() {
        mBinding!!.LayoutShowPassword.visibility = View.VISIBLE
    }

    override fun hidePassword() {
        mBinding!!.LayoutShowPassword.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenterGetData?.detachView()
    }
}