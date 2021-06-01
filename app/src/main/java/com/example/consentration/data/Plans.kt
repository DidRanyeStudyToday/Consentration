package com.example.consentration.data

import android.content.res.Resources
import com.example.consentration.R

/* Returns initial list of plans. */
fun planList(resources: Resources): List<Plan> {
    return listOf(
        Plan(
            id = 1,
            name = resources.getString(R.string.plan1_name),
            isDone = false
        ),
        Plan(
            id = 2,
            name = resources.getString(R.string.plan2_name),
            isDone = false
        ),
        Plan(
            id = 3,
            name = resources.getString(R.string.plan3_name),
            isDone = false
        ),
        Plan(
            id = 4,
            name = resources.getString(R.string.plan4_name),
            isDone = false
        ),
        Plan(
            id = 5,
            name = resources.getString(R.string.plan5_name),
            isDone = false
        ),
        Plan(
            id = 6,
            name = resources.getString(R.string.plan6_name),
            isDone = false
        ),
        Plan(
            id = 7,
            name = resources.getString(R.string.plan7_name),
            isDone = false
        ),
        Plan(
            id = 8,
            name = resources.getString(R.string.plan8_name),
            isDone = false
        ),
        Plan(
            id = 9,
            name = resources.getString(R.string.plan9_name),
            isDone = false
        ),
        Plan(
            id = 10,
            name = resources.getString(R.string.plan10_name),
            isDone = false
        ),
        Plan(
            id = 11,
            name = resources.getString(R.string.plan11_name),
            isDone = false
        )
    )
}