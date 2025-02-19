package com.todokanai.baseproject.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_user")
data class User(
    @ColumnInfo val number : Long?,
    @ColumnInfo var userValue : Int = 0
) {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo
    var no : Long? = null

}