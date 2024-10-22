package com.ninja.ninjarecipe.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], exportSchema = false, version = 1)
abstract class  AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}