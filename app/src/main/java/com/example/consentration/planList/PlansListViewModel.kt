package com.example.consentration.planList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.consentration.data.DataSource
import com.example.consentration.data.Plan
import kotlin.random.Random

class PlansListViewModel(val dataSource: DataSource) : ViewModel() {

    val plansLiveData = dataSource.getPlanList()

    /* If the name and description are present, create new Plan and add it to the datasource */
    fun insertPlan(planName: String?, planDescription: String?) {
        if (planName == null || planDescription == null) {
            return
        }

        val newPlan = Plan(
            Random.nextLong(),
            planName,
            false
        )

        dataSource.addPlan(newPlan)
    }
}

class PlansListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlansListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlansListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}