package com.example.consentration.ui.statistic

import android.icu.text.DecimalFormat
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter

class MyValueFormatter : ValueFormatter() {
    private val format = DecimalFormat("###,##0.0")

    override fun getPointLabel(entry: Entry?): String {
        return format.format(entry?.y)
    }

    override fun getBarLabel(barEntry: BarEntry?): String {
        return format.format(barEntry?.y)
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return format.format(value)
    }
}