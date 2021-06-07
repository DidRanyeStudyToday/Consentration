package com.example.consentration.ui.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.consentration.plan.Plan
import com.example.consentration.plan.PlansAdapter
import com.example.consentration.plan.planList
import java.time.LocalDate

class PlanViewModel(planList : MutableList<Plan>) : ViewModel() {

    var planList = planList

}

class PlanViewModelFactory(private val planList: MutableList<Plan>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlanViewModel(planList) as T
    }

}