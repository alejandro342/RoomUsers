package com.alejandro.roomproject.modules.users.profileuser.views


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alejandro.roomproject.R
import com.alejandro.roomproject.databinding.ActivityProfileUserBinding
import com.alejandro.roomproject.dialogs.closesession.DialogCloseSession
import com.alejandro.roomproject.modules.users.profileuser.interfaces.InterfaceProfileUser
import com.alejandro.roomproject.modules.users.profileuser.presenter.PresenterProfileUser


class ProfileUserActivity : AppCompatActivity(), View.OnClickListener, InterfaceProfileUser {
    private var mBinding: ActivityProfileUserBinding? = null
    private var mToolbar: Toolbar? = null
    private var mPresenterProfileUser: PresenterProfileUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityProfileUserBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        mPresenterProfileUser = PresenterProfileUser(this, this)
        mBinding!!.btnEditPassword.setOnClickListener(this)
        mBinding!!.imgCloseSession.setOnClickListener(this)
        myToolbar()

        mPresenterProfileUser?.getUserFromSession()
        mPresenterProfileUser?.setDataUser()
    }

    private fun myToolbar() {
        mToolbar = findViewById(R.id.my_toolbar)
        mToolbar?.title = "Perfil"
        mToolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        mToolbar?.titleMarginStart = 450
    }

    override fun onClick(mItem: View?) {
        when (mItem) {
            mBinding!!.btnEditPassword -> {
                mPresenterProfileUser?.gotoEditPassword()
            }
            mBinding!!.imgCloseSession -> {showDialogCloseSession()}
        }
    }
    fun showDialogCloseSession(){
        DialogCloseSession{closeSession->
            mPresenterProfileUser?.closeSession()
        }.show(supportFragmentManager,"dialog")
    }

    override fun setDataUser(name: String, user: String, email: String) {
        mBinding!!.textViewNameUserProfile.text = name
        mBinding!!.textViewNameUser.text = user
        mBinding!!.textViewEmailUser.text = email
    }

}