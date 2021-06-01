package com.example.consentration.ui.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.consentration.R
import com.example.consentration.databinding.FragmentClockBinding
import com.example.consentration.databinding.FragmentPlanBinding
import kotlin.let as let1

class PlanFragment : Fragment() {

    private lateinit var planViewModel: PlanViewModel
    private lateinit var binding: FragmentPlanBinding

    private val data = listOf("Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
            "Pineapple", "Strawberry", "Cherry", "Mango")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlanBinding.inflate(inflater)
        planViewModel =
                ViewModelProvider(this).get(PlanViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_plan, container, false)

        val todoListView: ListView = root.findViewById(R.id.todo_list)
        val adapter = ArrayAdapter<String>(this.requireActivity(), android.R.layout.simple_list_item_1, data)
        todoListView.adapter = adapter
        return root
    }
}