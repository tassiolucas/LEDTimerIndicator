package com.example.ledtimerindicator.Frontend

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import com.example.ledtimerindicator.CustomViews.LedDisplayView
import com.example.ledtimerindicator.R

class LedDisplayManager(activity: Activity) {

    private val FIRST_INDEX = 0
    private val FIRST = 1
    private val SECOND = 2
    private val THIRD = 3
    private val BASE_INDEX_MOD = 10
    private val SECOND_DIGIT_INDEX = 10
    private val THIRD_DIGIT_INDEX = 100

    private var firstDisplayLed: LedDisplayView = activity.findViewById(R.id.first_led_view)
    private var secondDisplayLed : LedDisplayView = activity.findViewById(R.id.second_led_view)
    private var thirdDisplayLed : LedDisplayView = activity.findViewById(R.id.third_led_view)

    private var firstDigit = 0
    private var secondDigit = 0
    private var thirdDigit = 0

    @SuppressLint("Range")
    fun setTime(time: String) {
        val digits = time.trim().split("\\s*,\\s*")[FIRST_INDEX]

        if (digits.length >= FIRST) {
            firstDigit = digits.toInt() % BASE_INDEX_MOD
        }

        if (digits.length >= SECOND) {
            secondDigit = (digits.toInt() / SECOND_DIGIT_INDEX) % BASE_INDEX_MOD
            secondDisplayLed.visibility = View.VISIBLE
        }
        else secondDisplayLed.visibility = View.GONE

        if (digits.length >= THIRD) {
            thirdDigit = (digits.toInt() / THIRD_DIGIT_INDEX) % BASE_INDEX_MOD
            thirdDisplayLed.visibility = View.VISIBLE
        }
        else thirdDisplayLed.visibility = View.GONE

        firstDisplayLed.setScreenValue(firstDigit)
        secondDisplayLed.setScreenValue(secondDigit)
        thirdDisplayLed.setScreenValue(thirdDigit)
    }

    fun getLedsColor() : Int {
        return firstDisplayLed.getLedColor()
    }

    fun setLedsColor(color: Int) {
        firstDisplayLed.setLedColor(color)
        secondDisplayLed.setLedColor(color)
        thirdDisplayLed.setLedColor(color)
    }

    fun setLedsSize(size: Int) {
        firstDisplayLed.setLayoutDimen(size)
        secondDisplayLed.setLayoutDimen(size)
        thirdDisplayLed.setLayoutDimen(size)
    }

    fun getLedsReferenceSize() : Int {
        return firstDisplayLed.getLedSize()
    }
}