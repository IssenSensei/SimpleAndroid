package com.issen.simpleandroid.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_posts")
data class LocalPost constructor(

    @ColumnInfo(name = "user_id")
    val userId: Int,

    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)