package com.example.ledtimerindicator.Interfaces

interface CountTimerListener {
    fun onStartCount()
    fun onTickCount(seconds: Long)
    fun onFinish()
}