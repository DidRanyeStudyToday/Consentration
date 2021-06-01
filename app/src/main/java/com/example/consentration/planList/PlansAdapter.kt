package com.example.consentration.planList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.consentration.R
import com.example.consentration.data.Plan

class PlansAdapter(private val onClick: (Plan) -> Unit) :
    ListAdapter<Plan, PlansAdapter.PlanViewHolder>(PlanDiffCallback) {

    /* ViewHolder for Plan, takes in the inflated view and the onClick behavior. */
    class PlanViewHolder(itemView: View, val onClick: (Plan) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val planTextView: TextView = itemView.findViewById(R.id.plan_name)
        private val planRadioButton: RadioButton = itemView.findViewById(R.id.plan_isDone)
        private var currentPlan: Plan? = null

        init {
            itemView.setOnClickListener {
                currentPlan?.let {
                    onClick(it)
                }
            }
        }

        /* Bind plan name and isDone. */
        fun bind(plan: Plan) {
            currentPlan = plan

            planTextView.text = plan.name
            planRadioButton.isChecked = plan.isDone
        }
    }

    /* Creates and inflates view and return PlanViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.plan_item, parent, false)
        return PlanViewHolder(view, onClick)
    }

    /* Gets current plan and uses it to bind view. */
    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val plan = getItem(position)
        holder.bind(plan)
    }
}

object PlanDiffCallback : DiffUtil.ItemCallback<Plan>() {
    override fun areItemsTheSame(oldItem: Plan, newItem: Plan): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Plan, newItem: Plan): Boolean {
        return oldItem.id == newItem.id
    }
}