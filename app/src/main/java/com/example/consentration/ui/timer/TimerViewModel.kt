package com.example.consentration.ui.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    private var _timerState = MutableLiveData<TimerActivity.TimerState>()

    private var _timerLength = MutableLiveData<Long>()

    private var _timerReamingLength = MutableLiveData<Long>()

    private var _previousTimerLength = MutableLiveData<Long>()

    val timerState: LiveData<TimerActivity.TimerState>
        get() {
            return _timerState
        }

    val timerLength: LiveData<Long>
        get() {
            return _timerLength
        }

    val timerReamingLength: LiveData<Long>
        get() {
            return _timerReamingLength
        }

    val previousTimerLength: LiveData<Long>
        get() {
            return _previousTimerLength
        }

    init {
        _timerState.value = TimerActivity.TimerState.Stopped
        _timerLength.value = 0L
        _timerReamingLength.value = 0L
        _previousTimerLength.value = 0L
    }

    fun startTimer() {
        _timerState.value = TimerActivity.TimerState.Running
    }

    fun pauseTimer() {
        _timerState.value = TimerActivity.TimerState.Paused
    }

    fun stopTimer() {
        _timerState.value = TimerActivity.TimerState.Stopped
    }

    fun setPreviousTimerLength(value: Long) {
        _previousTimerLength.value = value
    }
}