package com.example.consentration.planList

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.consentration.addPlan.AddPlanActivity
import com.example.consentration.planDetail.PlanDetailActivity
import com.example.consentration.R
import com.example.consentration.addPlan.PLAN_DESCRIPTION
import com.example.consentration.addPlan.PLAN_NAME
import com.example.consentration.data.Plan

const val PLAN_ID = "plan id"

class PlansListActivity : AppCompatActivity() {
    private val newPlanActivityRequestCode = 1
    private val plansListViewModel by viewModels<PlansListViewModel> {
        PlansListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Instantiates headerAdapter and plansAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter()
        val plansAdapter = PlansAdapter { plan -> adapterOnClick(plan) }
        val concatAdapter = ConcatAdapter(headerAdapter, plansAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        plansListViewModel.plansLiveData.observe(this, {
            it?.let {
                plansAdapter.submitList(it as MutableList<Plan>)
                headerAdapter.updatePlanCount(it.size)
            }
        })

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }
    }

    /* Opens PlanDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(plan: Plan) {
        val intent = Intent(this, PlanDetailActivity()::class.java)
        intent.putExtra(PLAN_ID, plan.id)
        startActivity(intent)
    }

    /* Adds plan to planList when FAB is clicked. */
    private fun fabOnClick() {
        val intent = Intent(this, AddPlanActivity::class.java)
        startActivityForResult(intent, newPlanActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts plan into viewModel. */
        if (requestCode == newPlanActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val planName = data.getStringExtra(PLAN_NAME)
                val planDescription = data.getStringExtra(PLAN_DESCRIPTION)

                plansListViewModel.insertPlan(planName, planDescription)
            }
        }
    }
}