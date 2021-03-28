package com.issen.simpleandroid.data.remote

import com.issen.simpleandroid.data.domain.Comment
import com.issen.simpleandroid.data.domain.Photo
import com.issen.simpleandroid.data.local.LocalPost

data class NetworkPost(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

data class NetworkComment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)

data class NetworkPhoto(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
)

fun NetworkComment.asDomainModel(): Comment {
    return Comment(
        postId,
        id,
        name,
        email,
        body
    )
}

fun NetworkPhoto.asDomainModel(): Photo {
    return Photo(
        albumId,
        id,
        title,
        url,
        thumbnailUrl
    )
}

fun List<NetworkPost>.asDatabaseModel(): List<LocalPost> {
    return map {
        LocalPost(
            it.userId,
            it.id,
            it.title,
            it.body
        )
    }
}