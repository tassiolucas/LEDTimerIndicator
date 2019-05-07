package com.example.ledtimerindicator.Backend

import android.os.CountDownTimer
import android.util.Log
import com.example.ledtimerindicator.Interfaces.CountTimerListener

object CountTimerManager {

    private val MILLIS = 1000L
    private val ZERO = 0L
    val MINIMUM_SECOND = 1
    val REDUCER_SECOND = 1

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
       counterIsInit = true
    }

    fun setTimeSeconds(seconds: Long) {
        if (!counterIsInit) {
            counter = createCounter(seconds)
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

        return object : CountDownTimer(((timeSeconds + MINIMUM_SECOND) * MILLIS), MILLIS) {
            override fun onTick(millisUntilFinished: Long) {
                timeSeconds -= REDUCER_SECOND

                CountTimerManager.countTimerListener.onTickCount(getTimeSeconds())

                if (getTimeSeconds() < MINIMUM_SECOND) {
                    CountTimerManager.countTimerListener.onFinish()

                    counter.onFinish()

                    counterIsInit = false
                }
            }

            override fun onFinish() {
                if (getTimeSeconds() <= ZERO) {
                    CountTimerManager.countTimerListener.onFinish()

                    counterIsInit = false
                }
            }
        }
    }
}