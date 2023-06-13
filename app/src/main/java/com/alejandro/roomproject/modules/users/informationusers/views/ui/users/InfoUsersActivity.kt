package com.alejandro.roomproject.modules.users.informationusers.views.ui.users

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.alejandro.roomproject.R
import com.alejandro.roomproject.data.entity.AppDatabase
import com.alejandro.roomproject.data.roomdb.RoomDataBase
import com.alejandro.roomproject.databinding.ActivityInfoUsersBinding
import com.alejandro.roomproject.extenciones.myToast
import com.alejandro.roomproject.models.Users
import com.alejandro.roomproject.modules.users.informationusers.interfaces.InterfaceUsers
import com.alejandro.roomproject.modules.users.informationusers.views.adapter.AdapterUsers
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InfoUsersActivity : AppCompatActivity(), InterfaceUsers {

    private var mBinding: ActivityInfoUsersBinding? = null
    var listUsers: MutableList<Users> = mutableListOf()
    lateinit var adapterUsers: AdapterUsers
    private var mToolbar: Toolbar? = null

    //Room
    private val miRoomDB = RoomDataBase.getInstance(this)
    private val miBD = miRoomDB.getMiBaseDeDatos()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityInfoUsersBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)

        mBinding!!.RcvUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getUsers(miBD)
        toolbar()
    }

    private fun toolbar() {
        mToolbar = findViewById(R.id.my_toolbar)
        mToolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        mToolbar?.title = "Información"
        mToolbar?.titleMarginStart = 350
    }

    private fun getUsers(room: AppDatabase) {
        lifecycleScope.launch(Dispatchers.IO) {
            listUsers = room.userDao().getUsers()
            adapterUsers = AdapterUsers(listUsers, this@InfoUsersActivity, this@InfoUsersActivity)
            mBinding!!.RcvUsers.adapter = adapterUsers
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onItemClick(user: Users) {

        if (user.imageUser.isNotEmpty()) {
            Picasso.get()
                .load(user.imageUser)
                .into(mBinding!!.imgUserInformation)
        } else {
            mBinding!!.imgUserInformation.setImageResource(R.drawable.ic_person)
        }

        mBinding!!.ViewNameUser.text = user.name
        mBinding!!.TextEmailUser.text = user.email

        if (user.isConnected) {
            mBinding!!.mStatusUser.text = "conectado"
        } else {
            mBinding!!.mStatusUser.text = "desconectado"
        }
    }
}