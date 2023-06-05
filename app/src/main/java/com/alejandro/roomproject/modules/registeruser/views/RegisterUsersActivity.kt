package com.alejandro.roomproject.modules.registeruser.views


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alejandro.roomproject.R
import com.alejandro.roomproject.databinding.ActivityRegisterUsersBinding
import com.alejandro.roomproject.models.Users
import com.alejandro.roomproject.modules.registeruser.presenter.RegisterPresenter

class RegisterUsersActivity : AppCompatActivity(), View.OnClickListener {

    private var mBinding: ActivityRegisterUsersBinding? = null
    private var mRegisterPresenter: RegisterPresenter? = null
    private var mToolbar: Toolbar? = null
    lateinit var mUser: Users
    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = ActivityRegisterUsersBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mBinding!!.root)
        toolbar()
        mRegisterPresenter = RegisterPresenter(this)
        mBinding!!.btnRegister.setOnClickListener(this)
    }

    fun toolbar() {
        mToolbar = findViewById(R.id.my_toolbar)
        mToolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.myColorOrange))
        mToolbar?.title = "Registrar usuario"
        mToolbar?.titleMarginStart = 250
    }


    override fun onClick(mItem: View?) {
        when (mItem) {
            mBinding!!.btnRegister -> mRegisterPresenter?.registerData(
                mBinding!!.editUser.text.toString().trim(),
                mBinding!!.editName.text.toString().trim(),
                mBinding!!.editEmail.text.toString().trim(),
                mBinding!!.editPassword.text.toString().trim(),
                mBinding!!.editRepeatPassword.text.toString().trim(),
                true
            )
        }
    }

}