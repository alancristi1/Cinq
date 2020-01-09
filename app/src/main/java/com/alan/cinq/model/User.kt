package com.alan.cinq.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var id : Int = 0,
    @ColumnInfo(name = "name") var name : String = "",
    @ColumnInfo(name = "lastname") var lastName : String = "",
    @ColumnInfo(name = "email") var email : String = ""
)