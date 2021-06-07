package com.example.consentration.ui.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.consentration.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "设置"
        val toolbar: androidx.appcompat.widget.Toolbar =
            findViewById(R.id.toolbar2)
        toolbar.setOnClickListener {
            finish()
        }
        toolbar.title = "设置"
    }
}