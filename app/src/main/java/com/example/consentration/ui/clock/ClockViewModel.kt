package com.example.consentration.ui.clock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.*
import java.util.*

class ClockViewModel : ViewModel() {

    private val current = MutableLiveData<LocalDate>()

    init {
        current.value =  LocalDate.now()
    }

    private val _year = MutableLiveData<String>().apply {
        value = current.value?.year.toString() + " 年"
    }

    private val _month = MutableLiveData<String>().apply {
        value = when (current.value?.month) {
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
            else -> "NULL"
        }
    }

    private val _day = MutableLiveData<String>().apply {
        value = current.value?.dayOfMonth.toString()
    }

    private val _dayOfWeek = MutableLiveData<String>().apply {
        value = when (current.value?.dayOfWeek) {
            DayOfWeek.MONDAY -> "M O N"
            DayOfWeek.TUESDAY -> "T U E"
            DayOfWeek.WEDNESDAY -> "W E D"
            DayOfWeek.THURSDAY -> "T H U"
            DayOfWeek.FRIDAY -> "F R I"
            DayOfWeek.SATURDAY -> "S A T"
            DayOfWeek.SUNDAY -> "S U N"
            else -> "NULL"
        }
    }

    private val _diff = MutableLiveData<Period>().apply {
        val theDay = LocalDate.of(2021, Month.DECEMBER, 26)
        val diff = Period.between(current.value, theDay)
        value = diff
    }

    val year: LiveData<String> get() = _year
    val month: LiveData<String> get() = _month
    val day: LiveData<String> get() = _day
    val dayOfWeek: LiveData<String> get() = _dayOfWeek
    val diff: LiveData<Period> get() = _diff
}