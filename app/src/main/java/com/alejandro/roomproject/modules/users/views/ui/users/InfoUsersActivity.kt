package com.alejandro.roomproject.modules.users.views.ui.users

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
import com.alejandro.roomproject.databinding.ActivityInfoUsersBinding
import com.alejandro.roomproject.extenciones.myToast
import com.alejandro.roomproject.models.Users
import com.alejandro.roomproject.modules.users.interfaces.InterfaceUsers
import com.alejandro.roomproject.modules.users.views.adapter.AdapterUsers
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InfoUsersActivity : AppCompatActivity(), InterfaceUsers {
    private var mBinding: ActivityInfoUsersBinding? = null
    var listUsers: MutableList<Users> = mutableListOf()
    lateinit var adapterUsers: AdapterUsers
    private var mToolbar: Toolbar? = null

    //Room
    lateinit var room: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityInfoUsersBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)

        mBinding!!.RcvUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        room = Room.databaseBuilder(this, AppDatabase::class.java, "my_database").build()
        getUsers(room)
        toolbar()
    }

    fun toolbar() {
        mToolbar = findViewById(R.id.my_toolbar)
        mToolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        mToolbar?.title = "Información"
        mToolbar?.titleMarginStart = 350
    }

    fun getUsers(room: AppDatabase) {
        lifecycleScope.launch(Dispatchers.IO) {
            listUsers = room.userDao().getUsuarios()
            Log.d("LOLOL", "${listUsers}")
            adapterUsers = AdapterUsers(listUsers, this@InfoUsersActivity)
            mBinding!!.RcvUsers.adapter = adapterUsers
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onItemClick(user: Users) {
        mBinding!!.ViewNameUser.text = user.name
        mBinding!!.TextEmailUser.text = user.email

        if (user.isConnected == true) {
            mBinding!!.mStatusUser.text = "conectado"
        } else {
            mBinding!!.mStatusUser.text = "desconectado"
        }
    }

}



































