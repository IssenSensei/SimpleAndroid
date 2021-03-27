package com.issen.simpleandroid.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface LocalPostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(localPost: LocalPost)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(localPosts: MutableList<LocalPost>)
}