package com.alejandro.roomproject.modules.getdatauser.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alejandro.roomproject.R
import com.alejandro.roomproject.databinding.ActivityGetDataUserBinding
import com.alejandro.roomproject.modules.getdatauser.interfaces.InterfaceGetDataUser
import com.alejandro.roomproject.modules.getdatauser.interfaces.InterfaceShowPassword
import com.alejandro.roomproject.modules.getdatauser.presenter.PresenterGetDataUser

class GetDataUserActivity : AppCompatActivity(), View.OnClickListener,
    InterfaceGetDataUser.ViewPassword, InterfaceShowPassword {

    private var mBinding: ActivityGetDataUserBinding? = null
    private var mPresenterGetData: PresenterGetDataUser? = null
    private var mToolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGetDataUserBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        myToolbar()
        mPresenterGetData = PresenterGetDataUser(this, this)
        mPresenterGetData?.attachView(this)
        mBinding!!.btnGetPassword.setOnClickListener(this)
    }

    private fun myToolbar() {
        mToolbar = findViewById(R.id.my_toolbar)
        mToolbar?.title = "obtener contraseña"
        mToolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        mToolbar?.titleMarginStart = 250
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