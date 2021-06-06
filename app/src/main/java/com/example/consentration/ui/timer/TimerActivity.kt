package com.example.consentration.ui.timer

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.consentration.R
import com.example.consentration.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {

    enum class TimerState {
        Stopped, Paused, Running
    }

    private lateinit var timerViewModel: TimerViewModel

    private lateinit var binding: ActivityTimerBinding

    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setIcon(R.drawable.ic_timer)
        supportActionBar?.title = "       番茄钟"

        binding.fabStart.setOnClickListener {
//            startTimer()
            timerViewModel.startTimer()
//            updateButtons()
        }

        binding.fabPause.setOnClickListener {
            timer.cancel()
            timerViewModel.pauseTimer()
//            updateButtons()
        }

        binding.fabStop.setOnClickListener {
            timer.cancel()

        }
    }

    override fun onResume() {
        super.onResume()

        initTimer()
    }

    override fun onPause() {
        super.onPause()

        if (timerViewModel.timerState.value == TimerState.Running) {

        } else if (timerViewModel.timerState.value == TimerState.Paused) {

        }

        timerViewModel.timerLength.value?.let { timerViewModel.setPreviousTimerLength(it) }
    }

    private fun initTimer() {
        val timerState = timerViewModel.timerState.value

        if (timerState == TimerState.Stopped) {
//            setNewTimerLength()
        }
        else{
//            setPreviousTimerLength()
        }

        if (timerState == TimerState.Stopped) {
            timerViewModel.timerReamingLength.value?.let { timerViewModel.timerLength.value }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.timer_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}