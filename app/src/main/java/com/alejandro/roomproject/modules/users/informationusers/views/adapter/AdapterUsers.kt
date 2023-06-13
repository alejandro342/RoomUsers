package com.alejandro.roomproject.modules.users.informationusers.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.alejandro.roomproject.R
import com.alejandro.roomproject.databinding.ItemUsersBinding
import com.alejandro.roomproject.models.Users
import com.alejandro.roomproject.modules.users.informationusers.interfaces.InterfaceUsers
import com.squareup.picasso.Picasso

class AdapterUsers(
    private val mUserList: MutableList<Users>,
    private val itemClickListener: InterfaceUsers,
    private val mContext: Context
) :
    RecyclerView.Adapter<AdapterUsers.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return UsersViewHolder(mView)
    }

    override fun getItemCount(): Int = mUserList.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = mUserList[position]

        if (user.isConnected) {
            holder.mBinding.ImgItemUser.borderColor =
                ContextCompat.getColor(mContext, R.color.myColorOn)
        } else {
            holder.mBinding.ImgItemUser.borderColor =
                ContextCompat.getColor(mContext, R.color.myColorOff)
        }

        Picasso.get()
            .load(user.imageUser)
            .into(holder.mBinding.ImgItemUser)
        holder.status(user)

        holder.itemView.setOnClickListener {
            holder.itemView.setOnClickListener {
                itemClickListener.onItemClick(user)
            }
        }
    }


    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mBinding = ItemUsersBinding.bind(itemView)


        fun status(statusUSer: Users) {
            if (statusUSer.isConnected) {
                mBinding.mViewItemStatus.setImageResource(R.drawable.shape_status_users_green)
            } else {
                mBinding.mViewItemStatus.setImageResource(R.drawable.shape_status_users)
            }
        }
    }
}