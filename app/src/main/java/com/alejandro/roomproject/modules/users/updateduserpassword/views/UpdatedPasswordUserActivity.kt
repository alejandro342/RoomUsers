package com.alejandro.roomproject.modules.users.updateduserpassword.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alejandro.roomproject.R
import com.alejandro.roomproject.databinding.ActivityUpdatedPasswordUserBinding
import com.alejandro.roomproject.dialogs.saveuser.DialogSaveUser
import com.alejandro.roomproject.dialogs.updateinformation.password.DialogChangePassword

class UpdatedPasswordUserActivity : AppCompatActivity(), View.OnClickListener {
    private var mBinding: ActivityUpdatedPasswordUserBinding? = null

    private var mToolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUpdatedPasswordUserBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        mBinding!!.btnConfirmChangePas.setOnClickListener(this)
        myToolbar()
    }

    private fun myToolbar() {
        mToolbar = findViewById(R.id.my_toolbar)
        mToolbar?.title = "Editar contraseña"
        mToolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        mToolbar?.titleMarginStart = 300
    }

    fun showDialog() {
        val mDialogChangePassword = DialogChangePassword()
        mDialogChangePassword.show(supportFragmentManager, "my_dialog")
    }

    override fun onClick(mItem: View?) {
        when (mItem) {
            mBinding!!.btnConfirmChangePas -> {
                showDialog()
            }
        }
    }

}