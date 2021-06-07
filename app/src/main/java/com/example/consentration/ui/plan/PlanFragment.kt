package com.example.consentration.ui.plan

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.consentration.databinding.FragmentPlanBinding
import com.example.consentration.plan.Plan
import com.example.consentration.plan.PlansAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class PlanFragment : Fragment() {

    private lateinit var planViewModel: PlanViewModel
    private lateinit var planList: MutableList<Plan>

    private var _binding: FragmentPlanBinding? = null
    private val  binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
         _binding = FragmentPlanBinding.inflate(inflater, container, false)


//        val serializedPlan = sharedPreferences.getString(USER_PROFILE, null)
//        val gson = Gson()
//        return gson.fromJson(serializedPlan, Plan::class.java)

        planList = mutableListOf()
        val sharedPreferences : SharedPreferences? = activity?.getPreferences(Context.MODE_PRIVATE)
        val json = sharedPreferences?.getString("planList_reserved", null)

        if(json!=null){
            val gson = Gson()
            planList = gson.fromJson(json, object : TypeToken<List<Plan?>?>() {}.type)
        }

        planViewModel =
                ViewModelProvider(this, PlanViewModelFactory(planList)).get(PlanViewModel::class.java)

        val root = binding.root

        val plansAdapter = PlansAdapter(planList)

        binding.rvTodoItems.adapter = plansAdapter

        binding.btnAddTodo.setOnClickListener {
            val todoTitle = binding.etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val plan = Plan(todoTitle, false)
                plansAdapter.addTodo(plan)
                binding.etTodoTitle.text.clear()
            }
        }
        binding.btnDeleteDoneTodos.setOnClickListener {
            plansAdapter.deleteDoneTodos()
        }


//        plansAdapter = PlansAdapter(planList)

//        binding.rvTodoItems.layoutManager = LinearLayoutManager(activity)
//        binding.rvTodoItems.adapter = plansAdapter
//
//        binding.btnAddTodo.setOnClickListener {
//            val todoTitle = binding.etTodoTitle.text.toString()
//            if(todoTitle.isNotEmpty()) {
//                val plan = Plan(todoTitle,false)
//                plansAdapter.addTodo(plan)
//                binding.etTodoTitle.text.clear()
//            }
//        }
//        binding.btnDeleteDoneTodos.setOnClickListener {
//            plansAdapter.deleteDoneTodos()
//        }


        return root
    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences : SharedPreferences? = activity?.getPreferences(Context.MODE_PRIVATE)
        val gson = Gson()
        val serializedPlan = gson.toJson(planList)
        sharedPreferences?.edit()?.putString("planList_reserved", serializedPlan)?.apply()



//        val gson = Gson()
//        val sharedPreferences: SharedPreferences = getSharedPreferences("ABC", Activity.MODE_PRIVATE)
//        val serializedPlan = gson.toJson(plan)
//        sharedPreferences.edit().putString(USER_PROFILE, serializedPlan).apply()
    }

}