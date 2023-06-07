package com.alejandro.roomproject.modules.users.informationusers.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alejandro.roomproject.R
import com.alejandro.roomproject.models.Users
import com.alejandro.roomproject.modules.users.informationusers.interfaces.InterfaceUsers
import com.squareup.picasso.Picasso

class AdapterUsers(val mUserList: MutableList<Users>, private val itemClickListener: InterfaceUsers) :
    RecyclerView.Adapter<AdapterUsers.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return UsersViewHolder(mView)
    }

    override fun getItemCount(): Int = mUserList.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = mUserList[position]
        Picasso.get()
            .load(R.drawable.shape_users)
            .into(holder.imgUser)
        holder.status(user)

        holder.itemView.setOnClickListener {
            holder.itemView.setOnClickListener {
                itemClickListener.onItemClick(user)
            }
        }
    }


    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgUser: ImageView? = null
        var mViewStatus: ImageView? = null

        init {
            imgUser = itemView.findViewById(R.id.Img_Item_User)
            mViewStatus = itemView.findViewById(R.id.mView_Item_Status)
        }
        fun status(statusUSer:Users){
            if (statusUSer.isConnected){
                mViewStatus?.setImageResource(R.drawable.shape_status_users_green)
            }else{
                mViewStatus?.setImageResource(R.drawable.shape_status_users)
            }
        }
    }
}