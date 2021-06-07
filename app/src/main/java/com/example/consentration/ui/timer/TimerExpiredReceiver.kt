package com.example.consentration.ui.timer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider

class TimerExpiredReceiver : BroadcastReceiver() {
    private lateinit var timerViewModel: TimerViewModel

    override fun onReceive(context: Context, intent: Intent) {
    }

}