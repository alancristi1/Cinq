package com.alan.cinq.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alan.cinq.model.User
import com.alan.cinq.model.dao.UserDao


@Database(entities = [User::class], version = 1)
    abstract class AppDatabase : RoomDatabase(){
        abstract fun userDao() : UserDao
}
