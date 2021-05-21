package com.example.consentration.ui.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.consentration.R

class StatisticFragment : Fragment() {

    private lateinit var statisticViewModel: StatisticViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        statisticViewModel =
                ViewModelProvider(this).get(StatisticViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_statistic, container, false)
        val textView: TextView = root.findViewById(R.id.text_statistic)
        statisticViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}