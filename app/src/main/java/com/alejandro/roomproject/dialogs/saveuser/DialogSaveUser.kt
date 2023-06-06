package com.alejandro.roomproject.dialogs.saveuser

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.alejandro.roomproject.databinding.DialogSaveUserBinding

class DialogSaveUser : DialogFragment() {

    private var mBinding: DialogSaveUserBinding? = null
    private var mDialog: Dialog? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = DialogSaveUserBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(mBinding?.root)

        cancelCloseSesion()
        mDialog = builder.create()
        mDialog?.setCanceledOnTouchOutside(false)
        mDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialog?.setCancelable(false)
        return mDialog as AlertDialog
    }

    fun cancelCloseSesion() {

        mBinding?.btnAcceptSaveUser?.setOnClickListener {
            mDialog?.dismiss()
        }
    }
}