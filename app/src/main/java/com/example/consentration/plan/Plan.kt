package com.example.consentration.plan

import android.app.Activity
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


data class Plan(
        @SerializedName("name") var name: String = "",
        @SerializedName("isDone") var isDone: Boolean = false
)