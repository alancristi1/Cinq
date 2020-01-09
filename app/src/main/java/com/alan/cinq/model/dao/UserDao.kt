package com.alan.cinq.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alan.cinq.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll() : List<User>

    @Query("SELECT * from user where id = :id")
    fun getUser(id : Int) : LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user : User)

    @Delete
    fun deleteUser(user : User)
}