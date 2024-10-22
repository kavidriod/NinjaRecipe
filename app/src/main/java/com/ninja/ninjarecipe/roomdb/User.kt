package com.ninja.ninjarecipe.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class User(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    var img: String,
    var tittle: String,
    var des: String,
    var ing: String,
    var category: String
)
