package com.alejandro.roomproject.modules.users.profileuser.views


import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alejandro.roomproject.R
import com.alejandro.roomproject.databinding.ActivityProfileUserBinding
import com.alejandro.roomproject.dialogs.closesession.DialogCloseSession
import com.alejandro.roomproject.dialogs.deleteuser.DialogDeleteUser
import com.alejandro.roomproject.extenciones.myToast
import com.alejandro.roomproject.modules.users.profileuser.interfaces.CallbackProfileUser
import com.alejandro.roomproject.modules.users.profileuser.interfaces.InterfaceViewProfile
import com.alejandro.roomproject.modules.users.profileuser.presenter.PresenterProfileUser
import com.github.dhaval2404.imagepicker.ImagePicker
import com.squareup.picasso.Picasso
import java.io.File


class ProfileUserActivity : AppCompatActivity(), View.OnClickListener,
    CallbackProfileUser, InterfaceViewProfile {

    private var mBinding: ActivityProfileUserBinding? = null
    private var mToolbar: Toolbar? = null
    private var mPresenterProfileUser: PresenterProfileUser? = null

    private var mImageFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityProfileUserBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        mPresenterProfileUser = PresenterProfileUser(this)
        mBinding!!.btnEditPassword.setOnClickListener(this)
        mBinding!!.imgCloseSession.setOnClickListener(this)
        mBinding!!.imgUpdateImageUser.setOnClickListener(this)
        mBinding!!.btnSaveImageUser.setOnClickListener(this)
        mBinding!!.cardDeleteUser.setOnClickListener(this)
        myToolbar()

        mPresenterProfileUser?.getUserFromSession()
        mPresenterProfileUser?.setDataCallback(this)
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

            mBinding!!.imgCloseSession -> {
                showDialogCloseSession()
            }

            mBinding!!.imgUpdateImageUser -> {
                selectImage()
            }

            mBinding!!.btnSaveImageUser -> {

            }

            mBinding!!.cardDeleteUser -> {
                showDialogDeleteUser()
            }
        }
    }

    private fun showDialogCloseSession() {
        DialogCloseSession {
            mPresenterProfileUser?.closeSession()
        }.show(supportFragmentManager, "dialog")
    }

    private fun showDialogDeleteUser() {
        DialogDeleteUser {
            mPresenterProfileUser?.deleteUser()
            mPresenterProfileUser?.closeSession()
        }.show(supportFragmentManager, "dialogDelete")
    }

    override fun setDataUser(
        name: String,
        user: String,
        email: String,
        status: Boolean,
        imageUser: String
    ) {
        mBinding!!.textViewNameUserProfile.text = name
        mBinding!!.textViewNameUser.text = user
        mBinding!!.textViewEmailUser.text = email
        Log.d("StatusUser", "${status}")
        if (status) {
            mBinding!!.textViewStatusUser.text = "conectado"

        } else {
            mBinding!!.textViewStatusUser.text = "desconectado"
        }

        if (imageUser.isNotEmpty()) {
            Picasso.get()
                .load(imageUser)
                .into(mBinding!!.ImgProfileUser)
        } else {
            mBinding!!.ImgProfileUser.setImageResource(R.drawable.ic_person)
        }

    }

    private val mStarImageForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            //data que devuelve la imagen que seleccione el usuario
            val data = result.data
            //se crea un archivo
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val mFieldUri = data?.data
                    if (mFieldUri != null) {
                        mBinding!!.ImgProfileUser.borderColor =
                            ContextCompat.getColor(this, R.color.myColorGreen)
                        mBinding!!.imgUpdateImageUser.borderColor =
                            ContextCompat.getColor(this, R.color.myColorGreen)
                        mImageFile = mFieldUri.path?.let { File(it) }
                        imageSelected(mFieldUri)
                    }
                    mBinding!!.ImgProfileUser.setImageURI(mFieldUri)
                }

                ImagePicker.RESULT_ERROR -> {
                    myToast(ImagePicker.getError(data))
                }

                else -> {
                    myToast("Proceso cancelado")
                }
            }
        }

    private fun selectImage() {
        ImagePicker.with(this)
            .crop()
            .compress(1024)
            .maxResultSize(1080, 1080)
            .createIntent { mIntent ->
                mStarImageForResult.launch(mIntent)

            }
    }

    override fun imageSelected(imageUri: Uri) {
        mPresenterProfileUser?.processImage(imageUri)
    }
}