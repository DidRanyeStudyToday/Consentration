package com.example.consentration.ui.clock

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.consentration.R
import com.example.consentration.databinding.FragmentClockBinding

class ClockFragment : Fragment() {
    private lateinit var clockViewModel: ClockViewModel
    private lateinit var binding: FragmentClockBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClockBinding.inflate(inflater)
        clockViewModel =
            ViewModelProvider(this).get(ClockViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_clock, container, false)

        val yearView: TextView = root.findViewById(R.id.current_year)
        clockViewModel.year.observe(viewLifecycleOwner, {
            yearView.text = it
        })

        val monthView: TextView = root.findViewById(R.id.current_month)
        clockViewModel.month.observe(viewLifecycleOwner, {
            monthView.text = it
        })

        val dayView: TextView = root.findViewById(R.id.current_day)
        clockViewModel.day.observe(viewLifecycleOwner, {
            dayView.text = it
        })

        val dayOfWeekView: TextView = root.findViewById(R.id.current_day_of_week)
        clockViewModel.dayOfWeek.observe(viewLifecycleOwner, {
            dayOfWeekView.text = it
        })

        val clockView: TextView = root.findViewById(R.id.clock)
        clockViewModel.diff.observe(viewLifecycleOwner, {
            clockView.text =
                getString(R.string.left_message, it.months.toString(), it.days.toString())
        })

        val clockFragment: LinearLayout = root.findViewById(R.id.clock_fragment)



        return root
    }
}