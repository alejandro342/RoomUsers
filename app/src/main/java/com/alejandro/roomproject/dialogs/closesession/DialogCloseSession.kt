package com.alejandro.roomproject.dialogs.closesession

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.alejandro.roomproject.databinding.DialogCloseSessionBinding
import com.alejandro.roomproject.modules.users.profileuser.interfaces.InterfaceProfileUser
import com.alejandro.roomproject.modules.users.profileuser.presenter.PresenterProfileUser

class DialogCloseSession(private val onClickCloSession: (Any?) -> Unit) : DialogFragment(),InterfaceProfileUser {

    private var mBinding: DialogCloseSessionBinding? = null
    private var mPresenterProfile: PresenterProfileUser? = null
    private var mDialog: Dialog? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = DialogCloseSessionBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(mBinding?.root)
        mPresenterProfile = PresenterProfileUser(requireContext(),this)

        closeSession()
        cancelCloseSession()
        mDialog = builder.create()
        mDialog?.setCanceledOnTouchOutside(false)
        mDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialog?.setCancelable(false)
        return mDialog as AlertDialog
    }


    private fun closeSession() {
        mBinding?.btnAcceptCloseSession?.setOnClickListener {
            onClickCloSession.invoke(
                mPresenterProfile?.closeSession()
            )
        }
    }

    private fun cancelCloseSession() {

        mBinding?.btnCancelCloseSession?.setOnClickListener {
            mDialog?.dismiss()
        }
    }

    override fun setDataUser(name: String, user: String, email: String) {

    }
}