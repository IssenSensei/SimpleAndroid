package com.issen.simpleandroid.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.issen.simpleandroid.data.domain.Feed
import com.issen.simpleandroid.data.domain.Post
import com.issen.simpleandroid.data.local.LocalPostDao
import com.issen.simpleandroid.data.local.asDomainModel
import com.issen.simpleandroid.data.remote.NetworkComment
import com.issen.simpleandroid.data.remote.NetworkPhoto
import com.issen.simpleandroid.data.remote.SimpleAndroidApi.simpleAndroidService
import com.issen.simpleandroid.data.remote.asDatabaseModel
import com.issen.simpleandroid.data.remote.asDomainModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class Repository(private val localPostDao: LocalPostDao) {

    val hasDataDownloadErrorOccurred = MutableLiveData<Boolean>()

    init {
        hasDataDownloadErrorOccurred.value = false
    }

    val postList: LiveData<List<Post>> = Transformations.map(localPostDao.getLocalPosts()) {
        it.asDomainModel()
    }

    val feedList = MutableLiveData<List<Feed>>()


    suspend fun getPosts() {
        withContext(Dispatchers.IO) {
            localPostDao.insert(simpleAndroidService.getPosts().asDatabaseModel())
        }
    }

    fun updateFeed() {
        val feedItems = mutableListOf<Feed>()
        val disposable =
            Observable.zip(
                simpleAndroidService.getPhotos().subscribeOn(Schedulers.io()),
                simpleAndroidService.getComments().subscribeOn(Schedulers.io())
                    .delay(3, TimeUnit.SECONDS),
                { list: List<NetworkPhoto>, list1: List<NetworkComment> -> list + list1 })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    feedList.postValue(feedItems)
                }
                .subscribe({ list ->
                    list.forEach {
                        if (it is NetworkPhoto) {
                            feedItems.add(Feed(1 to it.asDomainModel()))
                        } else {
                            feedItems.add(Feed(0 to (it as NetworkComment).asDomainModel()))
                        }
                    }
                }, {
                    hasDataDownloadErrorOccurred.postValue(true)
                })
    }

    fun clearError() {
        hasDataDownloadErrorOccurred.value = false
    }

}