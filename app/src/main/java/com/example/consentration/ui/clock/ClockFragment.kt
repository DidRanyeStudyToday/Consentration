package com.example.consentration.ui.clock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.consentration.R

class ClockFragment : Fragment() {

    private lateinit var clockViewModel: ClockViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        clockViewModel =
                ViewModelProvider(this).get(ClockViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_clock, container, false)
        val textView: TextView = root.findViewById(R.id.text_clock)
        clockViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}