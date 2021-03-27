package com.issen.simpleandroid.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.issen.simpleandroid.data.domain.Post

@Entity(tableName = "local_posts")
data class LocalPost constructor(

    @ColumnInfo(name = "user_id")
    val userId: Int,

    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)

fun List<LocalPost>.asDomainModel(): List<Post> {
    return map {
        Post(
            it.userId,
            it.id,
            it.title,
            it.body
        )
    }
}