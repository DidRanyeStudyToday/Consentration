package com.example.consentration.util

import android.content.Context

class PrefUtil {
    companion object {
        fun getTimerLength(context: Context): Long {
            val pref = context.getSharedPreferences("com.example.consentration_preferences", Context.MODE_PRIVATE)
            return (pref.getInt("timer_length", 45) * 60).toLong()
        }
    }
}