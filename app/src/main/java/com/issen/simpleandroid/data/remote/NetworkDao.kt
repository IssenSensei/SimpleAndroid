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

data class NetworkCommentContainer(val comments: List<NetworkComment>)

data class NetworkComment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)

data class NetworkPhotoContainer(val photos: List<NetworkPhoto>)

data class NetworkPhoto(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnail: String,
)

fun NetworkCommentContainer.asDomainModel(): List<Comment> {
    return comments.map {
        Comment(
            it.postId,
            it.id,
            it.name,
            it.email,
            it.body
        )
    }
}

fun NetworkPhotoContainer.asDomainModel(): List<Photo> {
    return photos.map {
        Photo(
            it.albumId,
            it.id,
            it.title,
            it.url,
            it.thumbnail
        )
    }
}


fun List<NetworkPost>.asDatabaseModel(): List<LocalPost>{
    return map {
        LocalPost(
            it.userId,
            it.id,
            it.title,
            it.body
        )
    }
}