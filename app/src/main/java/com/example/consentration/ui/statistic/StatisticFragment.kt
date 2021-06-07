package com.example.consentration.ui.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.consentration.R
import com.example.consentration.util.PrefUtil
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.util.*

class StatisticFragment : Fragment() {

    private lateinit var statisticViewModel: StatisticViewModel

    private lateinit var entries: ArrayList<Entry>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statisticViewModel =
            ViewModelProvider(this).get(StatisticViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_statistic, container, false)

        val chart: LineChart = root.findViewById(R.id.chart)

        entries = PrefUtil.getStudyTime(root.context)

        val dataSet = LineDataSet(entries, "label")



        val lineData = LineData(dataSet)
        lineData.setValueFormatter(MyValueFormatter())
        chart.data = lineData
        chart.isDragEnabled = true
        chart.isScaleYEnabled = true
        chart.isScaleXEnabled = true

        val xAxis = chart.xAxis
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)
        xAxis.setLabelCount(12, false)
        xAxis.granularity = 1F
        xAxis.spaceMax = 12F
        xAxis.spaceMin = 12F
        xAxis.axisMinimum = 1F
        xAxis.enableGridDashedLine(10f, 10f, 0f)

        val leftAxis: YAxis = chart.axisLeft
        leftAxis.setLabelCount(5, false)
        leftAxis.axisMinimum = 0f

        val rightAxis: YAxis = chart.axisRight
        rightAxis.isEnabled = false

        val description = chart.description
        description.text = "学习时间"
        chart.description = description

        val legend = chart.legend
        legend.isEnabled = false

        chart.invalidate()

        return root
    }
}