package com.example.consentration.addPlan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.consentration.R
import com.google.android.material.textfield.TextInputEditText

const val PLAN_NAME = "name"
const val PLAN_DESCRIPTION = "description"

class AddPlanActivity  : AppCompatActivity() {
    private lateinit var addPlanName: TextInputEditText
    private lateinit var addPlanDescription: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_plan_layout)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addPlan()
        }
        addPlanName = findViewById(R.id.add_plan_name)
        addPlanDescription = findViewById(R.id.add_plan_description)
    }

    /* The onClick action for the done button. Closes the activity and returns the new plan name
    and description as part of the intent. If the name or description are missing, the result is set
    to cancelled. */

    private fun addPlan() {
        val resultIntent = Intent()

        if (addPlanName.text.isNullOrEmpty() || addPlanDescription.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val name = addPlanName.text.toString()
            val description = addPlanDescription.text.toString()
            resultIntent.putExtra(PLAN_NAME, name)
            resultIntent.putExtra(PLAN_DESCRIPTION, description)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
}