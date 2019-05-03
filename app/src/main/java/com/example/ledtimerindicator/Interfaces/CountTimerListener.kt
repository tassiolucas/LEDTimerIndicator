package com.example.ledtimerindicator.Interfaces

interface CountTimerListener {
    fun onSetTime(seconds: Long)
    fun onStartCount()
    fun onTickCount(seconds: Long)
    fun onFinish()
}