package com.example.consentration.ui.timer

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.consentration.R
import com.example.consentration.databinding.ActivityTimerBinding
import com.example.consentration.util.PrefUtil
import java.util.*

class TimerActivity : AppCompatActivity() {

    enum class TimerState {
        Stopped, Paused, Running
    }

    companion object {
        fun setAlarm(
            context: Context,
            nowSeconds: Long,
            Remaining: Long,
            timerViewModel: TimerViewModel
        ): Long {
            val wakeUpTime = (nowSeconds + Remaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            timerViewModel.alarmSetTime.value = nowSeconds
            return wakeUpTime
        }

        fun removeAlarm(context: Context, timerViewModel: TimerViewModel) {
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            timerViewModel.alarmSetTime.value = 0L
        }

        val nowSeconds: Long
            get() = Calendar.getInstance().timeInMillis / 1000
    }

    private lateinit var timerViewModel: TimerViewModel

    private lateinit var binding: ActivityTimerBinding

    private lateinit var timer: CountDownTimer

    private var timerState = TimerState.Stopped

    private var timerLength = 60L * 45

    private var timerRemainLength = 60L * 45

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
            startTimer()
            timerViewModel.timerState.value = TimerState.Running
            updateButtons()
        }

        binding.fabPause.setOnClickListener {
            timer.cancel()
            timerViewModel.timerState.value = TimerState.Paused
            updateButtons()
        }

        binding.fabStop.setOnClickListener {
            timer.cancel()
            onTimerFinished()
        }
    }

    override fun onResume() {
        super.onResume()

        initTimer()

        removeAlarm(this, timerViewModel)
    }

    override fun onPause() {
        super.onPause()

        if (timerViewModel.timerState.value == TimerState.Running) {
            timer.cancel()
            val wakeUpTime = setAlarm(this, nowSeconds, timerRemainLength, timerViewModel)
        } else if (timerViewModel.timerState.value == TimerState.Paused) {

        }

        timerViewModel.previousTimerLength.value = timerLength
        timerViewModel.timerRemainLength.value = timerRemainLength
        timerViewModel.timerState.value = timerState
    }

    private fun initTimer() {
        timerState = timerViewModel.timerState.value!!

        if (timerState == TimerState.Stopped) {
            setNewTimerLength()
        } else {
            setPreviousTimerLength()
        }

        timerRemainLength =
            if (timerState == TimerState.Stopped) {
                timerLength
            } else {
                timerViewModel.timerRemainLength.value!!
            }

        val alarmSetTime = timerViewModel.alarmSetTime.value!!
        if (alarmSetTime > 0) {
            timerRemainLength -= nowSeconds - alarmSetTime
        }
        if (timerRemainLength <= 0)
            onTimerFinished()
        else if (timerState == TimerState.Running)
            startTimer()

        updateButtons()
        updateCountdownUI()
    }

    private fun onTimerFinished() {
        timerState = TimerState.Stopped

        setNewTimerLength()

        binding.timerContent.processCountdown.progress = 0

        timerViewModel.timerRemainLength.value = timerLength
        timerRemainLength = timerLength

        updateButtons()
        updateCountdownUI()
    }

    private fun startTimer() {
        timerState = TimerState.Running

        timer =
            object : CountDownTimer(timerRemainLength * 1000, 1000) {
                override fun onFinish() {
                    onTimerFinished()
                }

                override fun onTick(millisUntilFinished: Long) {
                    timerRemainLength = millisUntilFinished / 1000
                    updateCountdownUI()
                }
            }.start()

        updateButtons()
        updateCountdownUI()
    }

    private fun updateCountdownUI() {
        val seconds = timerRemainLength
        val minutes = seconds / 60
        val second = seconds - minutes * 60
        binding.timerContent.countDown.text = getString(R.string.timer_message, minutes, second)
        binding.timerContent.processCountdown.progress = (timerLength - timerRemainLength).toInt()
    }

    private fun updateButtons() {
        when (timerViewModel.timerState) {

        }
    }

    private fun setNewTimerLength() {
        binding.timerContent.processCountdown.max = PrefUtil.getTimerLength(this).toInt()
        timerLength = PrefUtil.getTimerLength(this)
    }

    private fun setPreviousTimerLength() {
        timerLength = timerViewModel.previousTimerLength.value!!
        binding.timerContent.processCountdown.max = timerLength.toInt()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.timer_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}