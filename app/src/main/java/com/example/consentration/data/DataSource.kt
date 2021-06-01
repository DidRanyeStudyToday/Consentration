package com.example.consentration.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/* Handles operations on plansLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialPlanList = planList(resources)
    private val plansLiveData = MutableLiveData(initialPlanList)

    /* Adds plan to liveData and posts value. */
    fun addPlan(plan: Plan) {
        val currentList = plansLiveData.value
        if (currentList == null) {
            plansLiveData.postValue(listOf(plan))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, plan)
            plansLiveData.postValue(updatedList)
        }
    }

    /* Removes plan from liveData and posts value. */
    fun removePlan(plan: Plan) {
        val currentList = plansLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(plan)
            plansLiveData.postValue(updatedList)
        }
    }

    /* Returns plan given an ID. */
    fun getPlanForId(id: Long): Plan? {
        plansLiveData.value?.let { plans ->
            return plans.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getPlanList(): LiveData<List<Plan>> {
        return plansLiveData
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}
