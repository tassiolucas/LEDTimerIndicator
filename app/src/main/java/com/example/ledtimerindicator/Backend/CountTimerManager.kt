package com.example.ledtimerindicator.Backend

import android.os.CountDownTimer
import android.util.Log
import com.example.ledtimerindicator.Interfaces.CountTimerListener

object CountTimerManager {

    private val millis = 1000L
    private var counter = 0L
    private var timeSeconds = 0L
    private lateinit var countTimerListener: CountTimerListener

    fun init(countTimerListener: CountTimerListener) {
        this.countTimerListener = countTimerListener
    }

    fun start() {

        object : CountDownTimer((timeSeconds * millis), millis) {
            override fun onTick(millisUntilFinished: Long) {
                timeSeconds -= 1

                Log.d("Tempo:", getTimeSeconds().toString())

                countTimerListener.onTickCount(getTimeSeconds())
            }

            override fun onFinish() {
                Log.d("Tempo:", "Finalizado!")

                countTimerListener.onFinish()
            }
        }.start()

    }

    fun setTimeSeconds(seconds: Long) {
        timeSeconds = seconds
        countTimerListener.onSetTime(seconds)
    }

    fun getTimeSeconds() : Long {
        return timeSeconds
    }
}