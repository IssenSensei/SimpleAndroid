package com.issen.simpleandroid.ui

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val _timerValue = MutableLiveData<Int>()
    val timerValue: LiveData<Int>
        get() = _timerValue

    private val _isCounting = MutableLiveData<Boolean>()
    val isCounting: LiveData<Boolean>
        get() = _isCounting

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
    }

    fun startTimer() {
        timer.start()
        _isCounting.value = true
    }

}