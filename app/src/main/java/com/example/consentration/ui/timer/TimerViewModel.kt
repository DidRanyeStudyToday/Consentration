package com.example.consentration.ui.timer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    var timerState = MutableLiveData<TimerActivity.TimerState>()

    var timerLength = MutableLiveData<Long>()

    var timerRemainLength = MutableLiveData<Long>()

    var previousTimerLength = MutableLiveData<Long>()

    var alarmSetTime = MutableLiveData<Long>()

    init {
        timerState.value = TimerActivity.TimerState.Stopped
        timerLength.value = 60L * 45
        timerRemainLength.value = 11110L
        previousTimerLength.value = 0L
        alarmSetTime.value = 0L
    }
}