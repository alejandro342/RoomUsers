package com.alejandro.roomproject.dialogs.updateinformation.password

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.alejandro.roomproject.databinding.DialogChangePasswordBinding
import com.alejandro.roomproject.modules.users.profileuser.presenter.PresenterProfileUser

class DialogChangePassword : DialogFragment() {

    private var mBinding: DialogChangePasswordBinding? = null
    private var mDialog: Dialog? = null
    private var mPresenterProfileUser: PresenterProfileUser? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = DialogChangePasswordBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(mBinding?.root)
        mPresenterProfileUser = PresenterProfileUser(requireContext())
        okDismissDialog()
        mDialog = builder.create()
        mDialog?.setCanceledOnTouchOutside(false)
        mDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialog?.setCancelable(false)
        return mDialog as AlertDialog
    }

    private fun okDismissDialog() {

        mBinding?.btnAcceptChangePassword?.setOnClickListener {
            mPresenterProfileUser?.closeSession()
            mDialog?.dismiss()
        }
    }

}