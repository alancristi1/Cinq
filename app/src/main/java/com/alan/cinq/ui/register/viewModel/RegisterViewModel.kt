package com.alan.cinq.ui.register.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alan.cinq.model.User
import com.alan.cinq.model.dao.UserDao
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.concurrent.thread

class RegisterViewModel : ViewModel(), KoinComponent {

    private val userDao : UserDao by inject()

    fun saveUser(name : String, lastName : String, email : String){
        val user = User()
        user.name = name
        user.lastName = lastName
        user.email = email
        thread {
            userDao.insertUser(user)
        }
    }

    fun getUser(id : Int) : LiveData<User>{
        return userDao.getUser(id)
    }

    fun deleteUser(user : User){
        thread {
            userDao.deleteUser(user)
        }
    }
}