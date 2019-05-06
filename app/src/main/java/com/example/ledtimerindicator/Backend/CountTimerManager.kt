package com.example.ledtimerindicator.Backend

import android.os.CountDownTimer
import android.util.Log
import com.example.ledtimerindicator.Interfaces.CountTimerListener

object CountTimerManager {
    private val millis = 1000L
    private var timeSeconds = 0L
    private lateinit var countTimerListener: CountTimerListener
    private lateinit var counter: CountDownTimer
    private var counterIsInit = false

    fun init(countTimerListener: CountTimerListener) {
        this.countTimerListener = countTimerListener
    }

    fun start() {
       counter.start()
       countTimerListener.onStartCount()
    }

    fun setTimeSeconds(seconds: Long) {
        if (!counterIsInit) {
            counter = createCounter(seconds)
            counterIsInit = true
        } else {
            counter.cancel()
            counter = createCounter(seconds).start()
        }
    }

    fun getTimeSeconds() : Long {
        return this.timeSeconds
    }

    fun createCounter(seconds: Long) : CountDownTimer {
        this.timeSeconds = seconds

        return object : CountDownTimer(((timeSeconds + 1) * millis), millis) {
            override fun onTick(millisUntilFinished: Long) {
                timeSeconds -= 1

                Log.d("Tempo:", getTimeSeconds().toString())

                CountTimerManager.countTimerListener.onTickCount(getTimeSeconds())

                if (getTimeSeconds() < 1) {
                    CountTimerManager.countTimerListener.onFinish()
                    counter.onFinish()
                    counterIsInit = false
                }
            }

            override fun onFinish() {
                if (getTimeSeconds() <= 0L) {
                    Log.d("Tempo Final:", getTimeSeconds().toString())
                    CountTimerManager.countTimerListener.onFinish()
                    counterIsInit = false
                }
            }
        }
    }
}