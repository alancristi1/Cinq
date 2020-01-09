package com.alan.cinq.ui.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alan.cinq.model.User
import com.alan.cinq.model.dao.UserDao
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.concurrent.thread

class MainViewModel : ViewModel(), KoinComponent {

    val userLiveData : MutableLiveData<List<User>> = MutableLiveData()
    private val userDao : UserDao by inject()

    fun getAllUser(){
        thread {
            userLiveData.postValue(userDao.getAll())
        }
    }
}
