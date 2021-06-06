package com.example.consentration.plan

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.consentration.R

class PlansAdapter(
    private val plans: MutableList<Plan>
) : RecyclerView.Adapter<PlansAdapter.PlanViewHolder>() {

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    class PlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /* Creates and inflates view and return PlanViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {

        return PlanViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.plan_item,
                parent,
                false
            )
        )
    }

    fun addTodo(plan: Plan) {
        plans.add(plan)
        notifyItemInserted(plans.size - 1)
    }

    fun deleteDoneTodos() {
        plans.removeAll { plan ->
            plan.isDone
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val plan = plans[position]

        holder.itemView.apply {
            val tvTodoTitle: TextView = findViewById(R.id.tvTodoTitle)
            val cbDone: CheckBox = findViewById(R.id.cbDone)
            tvTodoTitle.text = plan.name
            cbDone.isChecked = plan.isDone
            toggleStrikeThrough(tvTodoTitle, plan.isDone)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                plan.isDone = !plan.isDone
            }
        }
    }

    override fun getItemCount(): Int {
        return plans.size
    }
}