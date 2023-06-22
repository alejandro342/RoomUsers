package com.alejandro.roomproject.dialogs.deleteuser

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.alejandro.roomproject.databinding.DialogDeleteUserBinding
import com.alejandro.roomproject.modules.users.profileuser.presenter.PresenterProfileUser

class DialogDeleteUser(private val onClickDeleteUser: (Any?) -> Unit) : DialogFragment() {

    private var mBinding: DialogDeleteUserBinding? = null
    private var mPresenterProfileUser: PresenterProfileUser? = null
    private var mDialog: Dialog? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = DialogDeleteUserBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(mBinding?.root)
        mPresenterProfileUser = PresenterProfileUser(requireContext())

        deleteUser()
        cancelDeleteUser()

        mDialog = builder.create()
        mDialog?.setCanceledOnTouchOutside(false)
        mDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialog?.setCancelable(false)
        return mDialog as AlertDialog
    }

    private fun deleteUser() {
        mBinding?.btnAcceptDeleteUser?.setOnClickListener {
            onClickDeleteUser.invoke(
                mPresenterProfileUser?.deleteUser()
            )
        }
    }

    private fun cancelDeleteUser() {
        mBinding?.btnCancelDeleteUser?.setOnClickListener {
            mDialog?.dismiss()
        }
    }


}