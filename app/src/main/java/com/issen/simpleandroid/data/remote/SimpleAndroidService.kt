package com.issen.simpleandroid.data.remote

import io.reactivex.Observable
import retrofit2.http.GET

interface SimpleAndroidService {

    @GET("users/1/posts")
    suspend fun getPosts(): List<NetworkPost>

    @GET("posts/1/comments")
    fun getComments(): Observable<List<NetworkComment>>

    @GET("albums/1/photos")
    fun getPhotos(): Observable<List<NetworkPhoto>>
}
