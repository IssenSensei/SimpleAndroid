package com.issen.simpleandroid.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.issen.simpleandroid.data.domain.Feed
import com.issen.simpleandroid.data.domain.Post
import com.issen.simpleandroid.data.local.LocalPostDao
import com.issen.simpleandroid.data.local.asDomainModel
import com.issen.simpleandroid.data.remote.SimpleAndroidApi.simpleAndroidService
import com.issen.simpleandroid.data.remote.asDatabaseModel
import com.issen.simpleandroid.data.remote.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class Repository(private val localPostDao: LocalPostDao) {

    val postList: LiveData<List<Post>> = Transformations.map(localPostDao.getLocalPosts()) {
        it.asDomainModel()
    }

    suspend fun getPosts() {
        withContext(Dispatchers.IO) {
            localPostDao.insert(simpleAndroidService.getPosts().asDatabaseModel())
        }
    }

    suspend fun updateFeed(): MutableList<Feed> {
        val feedItems = mutableListOf<Feed>()
        withContext(Dispatchers.IO) {
            simpleAndroidService.getPhotos().forEach {
                feedItems.add(Feed(1 to it.asDomainModel()))
            }
            delay(3000)
            simpleAndroidService.getComments().forEach {
                feedItems.add(Feed(0 to it.asDomainModel()))
            }
        }
        return feedItems
    }

}