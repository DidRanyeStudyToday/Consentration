package com.example.consentration.util

import android.content.Context
import android.os.health.TimerStat
import android.preference.PreferenceActivity
import com.example.consentration.TimerActivity

class PrefUtil {
    companion object {
        fun getTimerLength(context: Context): Long {
            return 1
        }

        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "previous_timer_length_seconds"

        fun getPreviousTimerLengthSeconds(context: Context): Long {
            val prefs = context.getSharedPreferences("timer", Context.MODE_PRIVATE)
            return prefs.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
        }

        fun setPreviousTimerLengthSeconds(seconds: Long, context: Context) {
            val editor = context.getSharedPreferences("timer", Context.MODE_PRIVATE).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
            editor.apply()
        }

        private const val TIMERSTATE_ID = "timer_state"

        fun getTimerState(context: Context): TimerActivity.TimerState {
            val prefs = context.getSharedPreferences("timer", Context.MODE_PRIVATE)
            val ordinal = prefs.getInt(TIMERSTATE_ID, 0)
            return TimerActivity.TimerState.values()[ordinal]
        }

        fun setTimerState(context: Context, timerState: TimerActivity.TimerState) {
            val editor = context.getSharedPreferences("timer", Context.MODE_PRIVATE).edit()
            editor.putInt(TIMERSTATE_ID, timerState.ordinal)
            editor.apply()
        }
    }
}