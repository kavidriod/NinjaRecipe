package com.ninja.ninjarecipe.roomdb

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM recipe")
    fun getAll(): List<User>
}