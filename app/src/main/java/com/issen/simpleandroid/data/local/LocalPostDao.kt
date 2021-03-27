package com.issen.simpleandroid.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalPostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(localPost: LocalPost)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(localPosts: MutableList<LocalPost>)

    @Query("select * from local_posts")
    fun getLocalPosts(): LiveData<List<LocalPost>>
}