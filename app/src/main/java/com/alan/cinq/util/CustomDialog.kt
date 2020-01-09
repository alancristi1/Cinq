package com.alan.cinq.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.alan.cinq.R
import com.alan.cinq.ui.main.MainActivity
import com.alan.cinq.model.User
import com.alan.cinq.model.dao.UserDao
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.concurrent.thread

class CustomDialog : KoinComponent{

    private val userDao : UserDao by inject()

    fun showDeleteUser(context : Context, user : User){
        val builder = AlertDialog.Builder(context).create()
        val content : View = LayoutInflater.from(context).inflate(R.layout.dialog_delete, null)
        builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder.setCancelable(false)

        builder.setView(content)

        val btnYes = content.findViewById<Button>(R.id.btnYes)
        btnYes.setOnClickListener{
            thread {
                userDao.deleteUser(user)
            }
            builder.dismiss()

            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        val btnNo = content.findViewById<Button>(R.id.btnNo)
        btnNo.setOnClickListener{
            builder.dismiss()
        }

        builder.show()
    }
}