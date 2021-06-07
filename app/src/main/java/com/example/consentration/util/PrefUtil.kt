package com.example.consentration.util

import android.content.Context
import com.github.mikephil.charting.data.Entry
import java.util.*
import kotlin.collections.ArrayList

class PrefUtil {
    companion object {
        fun getTimerLength(context: Context): Long {
            val pref = context.getSharedPreferences(
                "com.example.consentration_preferences",
                Context.MODE_PRIVATE
            )
            return (pref.getInt("timer_length", 45) * 60).toLong()
        }

        fun addTodayStudyTime(context: Context, studyTime: Long) {
            val pref = context.getSharedPreferences("com.example.study_time", Context.MODE_PRIVATE)
            val editor = pref.edit()
            val startDay =
                if (pref.getLong("start_time", -1) == -1L) {
                    editor.putLong(
                        "start_time",
                        Calendar.getInstance().timeInMillis / (1000L * 60 * 60 * 24)
                    )
                    Calendar.getInstance().timeInMillis / (1000L * 60 * 60 * 24)
                } else {
                    pref.getLong("start_time", -1)
                }

            val currentDay = Calendar.getInstance().timeInMillis / (1000L * 60 * 60 * 24)
            val index = currentDay - startDay + 1
            if (pref.getFloat(index.toString(), -1.0f) == -1.0f) {
                editor.putFloat(index.toString(), studyTime.toFloat() / 60.0f / 60.0f)
            } else {
                val to =
                    pref.getFloat(index.toString(), -1.0f) + studyTime.toFloat() / 60.0f / 60.0f
                editor.putFloat(index.toString(), to)
            }
            editor.apply()
        }

        fun getStudyTime(context: Context): ArrayList<Entry> {
            val entries = java.util.ArrayList<Entry>()
            val pref = context.getSharedPreferences("com.example.study_time", Context.MODE_PRIVATE)
            if (pref.getLong("start_time", -1) == -1L) {
                return ArrayList()
            }
            val startTime = pref.getLong("start_time", -1)
            val todayTime = Calendar.getInstance().timeInMillis / (1000L * 60 * 60 * 24)
            for (i in 1..(todayTime - startTime + 1)) {
                val studyTime = pref.getFloat(i.toString(), -1f)
                if (studyTime == -1f) {
                    entries.add(Entry(i.toFloat(), 0f))
                } else {
                    entries.add(Entry(i.toFloat(), studyTime))
                }
            }
            return entries
        }

    }
}