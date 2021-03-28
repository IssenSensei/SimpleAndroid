package com.issen.simpleandroid.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.CountDownTimer
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issen.simpleandroid.data.Repository
import kotlinx.coroutines.launch


class MainActivityViewModel(private val repository: Repository) : ViewModel() {

    private val _timerValue = MutableLiveData<Int>()
    val timerValue: LiveData<Int>
        get() = _timerValue

    private val _isCounting = MutableLiveData<Boolean>()
    val isCounting: LiveData<Boolean>
        get() = _isCounting

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _hasInternetErrorOccurred = MutableLiveData<Boolean>()
    val hasInternetErrorOccurred: LiveData<Boolean>
        get() = _hasInternetErrorOccurred

    var postList = repository.postList
    var feedList = repository.feedList
    var hasDataDownloadErrorOccurred = repository.hasDataDownloadErrorOccurred

    private val timer = object : CountDownTimer(60000, 1000) {
        override fun onTick(millisecondsToFinish: Long) {
            _timerValue.value = (millisecondsToFinish / 1000).toInt()
        }

        override fun onFinish() {
            _isCounting.value = false
        }
    }

    init {
        _isCounting.value = false
        _isRefreshing.value = false
    }

    fun startQuery() {
        startTimer()
        viewModelScope.launch {
            repository.getPosts()
        }
    }

    private fun startTimer() {
        timer.start()
        _isCounting.value = true
    }

    private fun refresh() {
        _isRefreshing.value = true
        viewModelScope.launch {
            updateFeed()
        }
    }

    private fun updateFeed() {
        viewModelScope.launch {
            repository.updateFeed()
            _isRefreshing.value = false
        }
    }

    fun clearDataDownloadError() {
        repository.clearError()
    }

    fun clearInternetError() {
        _hasInternetErrorOccurred.value = false
    }

    fun checkIsOnline(context: Context) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if (activeNetwork?.isConnectedOrConnecting == true) {
            refresh()
        } else {
            _hasInternetErrorOccurred.value = true
        }
    }
}