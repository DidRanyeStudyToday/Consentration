package com.example.consentration.ui.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlanViewModel : ViewModel() {

    private val _plan = MutableLiveData<List<String> >().apply {
        val data = listOf("Apple", "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
            "Pineapple", "Strawberry", "Cherry", "Mango")
        value = data
    }
    val plan: LiveData<List<String> > get() = _plan
}