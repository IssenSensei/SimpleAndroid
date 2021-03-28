package com.issen.simpleandroid.ui

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issen.simpleandroid.data.Repository
import com.issen.simpleandroid.data.domain.Feed
import kotlinx.coroutines.delay
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

    private val _feedList = MutableLiveData<List<Feed>>()
    val feedList: LiveData<List<Feed>>
        get() = _feedList

    var postList = repository.postList

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

    fun refresh() {
        _isRefreshing.value = true
        viewModelScope.launch {
            updateFeed()
        }
    }

    private fun updateFeed() {
        viewModelScope.launch {
            _feedList.value = repository.updateFeed()
            _isRefreshing.value = false
        }
    }

}