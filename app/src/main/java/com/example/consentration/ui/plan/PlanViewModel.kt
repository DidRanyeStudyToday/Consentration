package com.example.consentration.ui.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.consentration.plan.Plan
import com.example.consentration.plan.PlansAdapter
import com.example.consentration.plan.planList
import java.time.LocalDate

class PlanViewModel : ViewModel() {
//    private val _planListLiveData = MutableLiveData<List<Plan> >().apply {
//        value = mutableListOf()
//    }
//
//    val planListLiveData: LiveData<List<Plan> > = _planListLiveData

//    private val _plansAdapter = MutableLiveData<PlansAdapter>().apply{
//        value = PlansAdapter(mutableListOf())
//    }
//
//    val plansAdapter: LiveData<PlansAdapter> get() = _plansAdapter

//    val planListLiveData : MutableLiveData< MutableList<Plan> > = MutableLiveData(mutableListOf())

    private var _planListLiveData = MutableLiveData< MutableList<Plan> >()

    init {
        _planListLiveData.value = mutableListOf()
    }

    val planListLiveData: LiveData<MutableList<Plan>> get() = _planListLiveData



}