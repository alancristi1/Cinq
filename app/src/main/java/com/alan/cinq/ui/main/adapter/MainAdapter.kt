package com.alan.cinq.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alan.cinq.R
import com.alan.cinq.model.User
import com.alan.cinq.ui.register.RegisterActivity
import com.alan.cinq.util.CustomDialog
import kotlinx.android.synthetic.main.item_user.view.*

class MainAdapter(private val item : List<User>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(item[position])
        holder.itemView.layoutItem.setOnClickListener {
            val intent = Intent(holder.itemView.context, RegisterActivity::class.java)
            intent.putExtra("id", item[position].id)
            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.layoutItem.setOnLongClickListener {
            val dialog = CustomDialog()
            dialog.showDeleteUser(holder.itemView.context, item[position])
            return@setOnLongClickListener true
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val name = itemView.item_name
        private val lastName = itemView.item_lastName
        private val email = itemView.item_email

        fun bindView(item : User){
            name.text = item.name
            lastName.text = item.lastName
            email.text = item.email
        }
    }
}