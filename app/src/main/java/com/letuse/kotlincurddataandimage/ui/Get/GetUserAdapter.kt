package com.letuse.kotlincurddataandimage.ui.Get

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.letuse.kotlincurddataandimage.R
import com.letuse.kotlincurddataandimage.model.UserModelItem
import kotlinx.android.synthetic.main.item_users.view.*

class GetUserAdapter (var UserList : List<UserModelItem> = ArrayList<UserModelItem>()) : RecyclerView.Adapter<GetUserAdapter.UserViewHolder>(){

    var userClickListener : UserClickListener ?= null

    fun setOnClickListener(clickListener: UserClickListener){
        this.userClickListener = clickListener
    }

    inner class UserViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) , View.OnClickListener {

        lateinit var userModelItem : UserModelItem

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(userModelItem: UserModelItem){
            this.userModelItem = userModelItem
            itemView.txtName.text = userModelItem.name.toString()
            itemView.txtId.text = userModelItem.id.toString()
        }

        override fun onClick(p0: View?) {
            userClickListener?.onClick(userModelItem)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return UserList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(UserList[position])
    }

    fun updateUsers(users : List<UserModelItem>){
        this.UserList = users
        notifyDataSetChanged()
    }

    interface UserClickListener{
        fun onClick(userModelItem: UserModelItem)
    }
}