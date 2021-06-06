package com.example.consentration.ui.timer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    var timerState = MutableLiveData<TimerActivity.TimerState>()

    var timerLength = MutableLiveData<Long>()

    var timerRemainLength = MutableLiveData<Long>()

    var previousTimerLength = MutableLiveData<Long>()

//    val timerState: LiveData<TimerActivity.TimerState>
//        get() {
//            return _timerState
//        }
//
//    val timerLength: LiveData<Long>
//        get() {
//            return _timerLength
//        }
//
//    val timerReamingLength: LiveData<Long>
//        get() {
//            return _timerReamingLength
//        }
//
//    val previousTimerLength: LiveData<Long>
//        get() {
//            return _previousTimerLength
//        }
//
    init {
        timerState.value = TimerActivity.TimerState.Stopped
        timerLength.value = 0L
        timerRemainLength.value = 11110L
        previousTimerLength.value = 0L
    }
//
//    fun startTimer() {
//        _timerState.value = TimerActivity.TimerState.Running
//    }
//
//    fun pauseTimer() {
//        _timerState.value = TimerActivity.TimerState.Paused
//    }
//
//    fun stopTimer() {
//        _timerState.value = TimerActivity.TimerState.Stopped
//    }
//
//    fun setPreviousTimerLength(value: Long) {
//        _previousTimerLength.value = value
//    }
}