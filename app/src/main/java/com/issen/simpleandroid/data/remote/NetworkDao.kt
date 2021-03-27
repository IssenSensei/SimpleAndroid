package com.issen.simpleandroid.data.remote

import com.issen.simpleandroid.data.domain.Comment
import com.issen.simpleandroid.data.domain.Photo
import com.issen.simpleandroid.data.domain.Post
import com.issen.simpleandroid.data.local.LocalPost
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkPostContainer(val posts: List<NetworkPost>)

@JsonClass(generateAdapter = true)
data class NetworkPost(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

@JsonClass(generateAdapter = true)
data class NetworkCommentContainer(val comments: List<NetworkComment>)

@JsonClass(generateAdapter = true)
data class NetworkComment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)

@JsonClass(generateAdapter = true)
data class NetworkPhotoContainer(val photos: List<NetworkPhoto>)

@JsonClass(generateAdapter = true)
data class NetworkPhoto(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnail: String,
)


fun NetworkPostContainer.asDomainModel(): List<Post> {
    return posts.map {
        Post(
            it.userId,
            it.id,
            it.title,
            it.body
        )
    }
}

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



fun NetworkPostContainer.asDatabaseModel(): List<LocalPost> {
    return posts.map {
        LocalPost(
            it.userId,
            it.id,
            it.title,
            it.body
        )
    }
}