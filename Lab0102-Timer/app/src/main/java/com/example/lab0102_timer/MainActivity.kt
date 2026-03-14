package com.example.lab0102_timer

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.lab0102_timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isRunning = false
    private var timeInSeconds = 0
    private val KEY_TIME = "saved_time_in_seconds"
    private val KEY_RUNNING = "is_timer_running"

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                timeInSeconds++
                updateTimerText()
                handler.postDelayed(this, 10)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButtonState(false)
        binding.btnReset.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#10002B"))

        if (savedInstanceState != null) {
            timeInSeconds = savedInstanceState.getInt(KEY_TIME, 0)
            isRunning = savedInstanceState.getBoolean(KEY_RUNNING, false)

            updateTimerText()

            if (isRunning) {
                setButtonState(true)
                handler.post(runnable)
            } else {
                setButtonState(false)
            }
        }

        binding.btnStartStop.setOnClickListener {
            if (isRunning) {
                stopTimer()
            } else {
                startTimer()
            }
        }

        binding.btnReset.setOnClickListener {
            resetTimer()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_TIME, timeInSeconds)
        outState.putBoolean(KEY_RUNNING, isRunning)
    }

    private fun startTimer() {
        if (!isRunning) {
            isRunning = true
            setButtonState(true)
            handler.post(runnable)
        }
    }

    private fun stopTimer() {
        isRunning = false
        setButtonState(false)
        handler.removeCallbacks(runnable)
    }

    private fun resetTimer() {
        stopTimer()
        timeInSeconds = 0
        updateTimerText()
    }

    private fun updateTimerText() {
        val hours = timeInSeconds / 3600
        val minutes = (timeInSeconds % 3600) / 60
        val seconds = timeInSeconds % 60
        val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)

        binding.tvTimer.text = timeString
    }

    private fun setButtonState(isTimerRunning: Boolean) {
        if (isTimerRunning) {
            binding.btnStartStop.text = "Stop"
            binding.btnStartStop.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#5A189A"))
        } else {
            binding.btnStartStop.text = "Start"
            binding.btnStartStop.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#7B2CBF"))
        }
    }
}