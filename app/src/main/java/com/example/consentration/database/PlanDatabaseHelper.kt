package com.example.consentration.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class PlanDatabaseHelper (private val context: Context, name: String, version: Int) :
        SQLiteOpenHelper(context, name, null, version) {

    private val createPlan = "create table plan_table (" +
            " id integer primary key autoincrement," +
            "topic text," +
            "isUrgent integer," +
            "isDone integer)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createPlan)
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists plan_table")
        onCreate(db)
    }

}