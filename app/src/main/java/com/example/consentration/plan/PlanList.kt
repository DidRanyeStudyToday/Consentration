package com.example.consentration.plan

import android.content.res.Resources
import com.example.consentration.R

fun planList(): List<Plan> {
    return listOf(
            Plan(
                    name = R.string.plan1_name.toString(),
                    isDone = false
            ),
            Plan(
                    name = R.string.plan2_name.toString(),
                    isDone = false
            ),
            Plan(
                    name = R.string.plan3_name.toString(),
                    isDone = false
            ),
    )
}