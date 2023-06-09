package com.alejandro.roomproject.dialogs.closesession

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.alejandro.roomproject.databinding.DialogCloseSessionBinding
import com.alejandro.roomproject.models.Users
import com.alejandro.roomproject.modules.users.profileuser.presenter.PresenterProfileUser
import com.alejandro.roomproject.utils.SharedPref
import com.google.gson.Gson

class DialogCloseSession(private val onClickCloSession: (Any?) -> Unit) : DialogFragment() {

    private var mBinding: DialogCloseSessionBinding? = null
    private var mPresenterProfile: PresenterProfileUser? = null

    private var mDialog: Dialog? = null

    private var mUser: Users? = null
    private val gson = Gson()

    private var sharedPref: SharedPref? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = DialogCloseSessionBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(mBinding?.root)
        mPresenterProfile = PresenterProfileUser(requireContext())

        closeSession()
        cancelCloseSession()
        sharedPref = SharedPref(requireActivity())
        mUser = gson.fromJson(sharedPref?.getInformation("user"), Users::class.java)

        mDialog = builder.create()
        mDialog?.setCanceledOnTouchOutside(false)
        mDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialog?.setCancelable(false)
        return mDialog as AlertDialog

    }


    private fun closeSession() {
        mBinding?.btnAcceptCloseSession?.setOnClickListener {
            onClickCloSession.invoke(
                mPresenterProfile?.upDateStatus(mUser!!.usuario, false)
            )
        }
    }

    private fun cancelCloseSession() {

        mBinding?.btnCancelCloseSession?.setOnClickListener {
            mDialog?.dismiss()
        }
    }
}