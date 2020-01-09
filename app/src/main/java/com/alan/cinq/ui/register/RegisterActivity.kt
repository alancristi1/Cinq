package com.alan.cinq.ui.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alan.cinq.R
import com.alan.cinq.ui.main.MainActivity
import com.alan.cinq.model.User
import com.alan.cinq.ui.register.viewModel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var userLiveData : LiveData<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val id  = intent.getIntExtra("id", 0)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

        if(id != 0){
            userLiveData = viewModel.getUser(id)
            userLiveData.observe(this, Observer {
                if(it != null){
                    inputName.text = Editable.Factory.getInstance().newEditable(it.name)
                    inputLastName.text = Editable.Factory.getInstance().newEditable(it.lastName)
                    inputEmail.text = Editable.Factory.getInstance().newEditable(it.email)
                }
            })
        }

        btnFinish.setOnClickListener {
            finish()
        }

        btnSave.setOnClickListener {

            if(id != 0){
                userLiveData.observe(this, Observer {
                    viewModel.deleteUser(it)
                })

            }

            viewModel.saveUser(inputName.text.toString(), inputLastName.text.toString(), inputEmail.text.toString())
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}