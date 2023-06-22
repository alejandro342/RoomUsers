package com.alejandro.roomproject.modules.menu.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alejandro.roomproject.R
import com.alejandro.roomproject.databinding.ActivityMenuBinding
import com.alejandro.roomproject.modules.menu.presenter.PresenterMenu

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    var mBinding: ActivityMenuBinding? = null
    var mPresenterMenu: PresenterMenu? = null

    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        mPresenterMenu = PresenterMenu(this)
        mBinding!!.goToViewUsers.setOnClickListener(this)
        mBinding!!.goToRegisterUsers.setOnClickListener(this)
        mBinding!!.goToViewProfile.setOnClickListener(this)
        toolbar()
    }

    fun toolbar() {
        mToolbar = findViewById(R.id.my_toolbar)
        mToolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        mToolbar?.title = "Menu"
        mToolbar?.titleMarginStart = 450
    }

    override fun onClick(mItem: View?) {
        when (mItem) {

            mBinding!!.goToViewUsers -> {
                mPresenterMenu?.goToInformation()
            }

            mBinding!!.goToRegisterUsers -> {
                mPresenterMenu?.goToRegister()
            }

            mBinding!!.goToViewProfile -> {
                mPresenterMenu?.goToProfile()
            }
        }
    }
}