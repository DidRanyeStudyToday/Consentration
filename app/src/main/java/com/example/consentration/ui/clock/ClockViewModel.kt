package com.example.consentration.ui.clock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.Period
import java.util.*

class ClockViewModel : ViewModel() {

    private val _month = MutableLiveData<String>().apply {
        val current = LocalDate.now()
        value = when (current.month) {
            Month.JANUARY -> "一月"
            Month.FEBRUARY -> "二月"
            Month.MARCH -> "三月"
            Month.APRIL -> "四月"
            Month.MAY -> "五月"
            Month.JUNE -> "六月"
            Month.JULY -> "七月"
            Month.AUGUST -> "八月"
            Month.SEPTEMBER -> "九月"
            Month.OCTOBER -> "十月"
            Month.NOVEMBER -> "十一月"
            Month.DECEMBER -> "十二月"
            null -> "无"
        }

    }

    private val _day = MutableLiveData<String>().apply {
        val current = LocalDateTime.now()
        value = current.dayOfMonth.toString()
    }

    private val _diff = MutableLiveData<Period>().apply {
        val current = LocalDate.now()
        val theDay = LocalDate.of(2021, Month.DECEMBER, 26)

        val diff = Period.between(current, theDay)
        value = diff
    }

    val month: LiveData<String> = _month
    val day: LiveData<String> = _day
    val diff: LiveData<Period> = _diff
}