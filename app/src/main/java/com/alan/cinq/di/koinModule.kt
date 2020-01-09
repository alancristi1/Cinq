package com.alan.cinq.di

import androidx.room.Room
import com.alan.cinq.room.AppDatabase
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "cinq-database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single {get<AppDatabase>().userDao()}
}