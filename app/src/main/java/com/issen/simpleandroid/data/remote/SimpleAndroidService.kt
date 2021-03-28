package com.issen.simpleandroid.data.remote

import retrofit2.http.GET

interface SimpleAndroidService {

    @GET("users/1/posts")
    suspend fun getPosts(): List<NetworkPost>

    @GET("posts/1/comments")
    suspend fun getComments(): List<NetworkComment>

    @GET("albums/1/photos")
    suspend fun getPhotos(): List<NetworkPhoto>
}
