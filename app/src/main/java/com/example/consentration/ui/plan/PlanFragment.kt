package com.example.consentration.ui.plan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consentration.R
import com.example.consentration.databinding.FragmentPlanBinding
import com.example.consentration.plan.Plan
import com.example.consentration.plan.PlansAdapter

class PlanFragment : Fragment() {

    private lateinit var planViewModel: PlanViewModel
//    private lateinit var binding: FragmentPlanBinding
    private lateinit var plansAdapter: PlansAdapter

    private var _binding: FragmentPlanBinding? = null
    private val  binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        binding = FragmentPlanBinding.inflate(inflater, container, false)
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        planViewModel =
                ViewModelProvider(this).get(PlanViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_plan, container, false)
        val root = binding.root

        plansAdapter = PlansAdapter(mutableListOf())

//        binding.rvTodoItems.layoutManager = LinearLayoutManager(activity)
        binding.rvTodoItems.adapter = plansAdapter

        binding.btnAddTodo.setOnClickListener {
            val todoTitle = binding.etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val plan = Plan(todoTitle,false)
                plansAdapter.addTodo(plan)
                binding.etTodoTitle.text.clear()
            }
        }
        binding.btnDeleteDoneTodos.setOnClickListener {
            plansAdapter.deleteDoneTodos()
        }


        return root
    }
}