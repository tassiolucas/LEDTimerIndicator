package com.example.ledtimerindicator.Frontend

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import com.example.ledtimerindicator.CustomViews.LedDisplayView
import com.example.ledtimerindicator.R

class LedDisplayManager(activity: Activity) {

    private var firstDisplayLed: LedDisplayView = activity.findViewById(R.id.first_led_view)
    private var secondDisplayLed : LedDisplayView = activity.findViewById(R.id.second_led_view)
    private var thirdDisplayLed : LedDisplayView = activity.findViewById(R.id.third_led_view)

    private var firstDigit = 0
    private var secondDigit = 0
    private var thirdDigit = 0

    @SuppressLint("Range")
    fun setTime(time: String) {
        val digits = time.trim().split("\\s*,\\s*")[0]

        if (digits.length >= 1) {
            firstDigit = digits.toInt() % 10
        }

        if (digits.length >= 2) {
            secondDigit = (digits.toInt() / 10) % 10
            secondDisplayLed.visibility = View.VISIBLE
        }
        else secondDisplayLed.visibility = View.GONE

        if (digits.length >= 3) {
            thirdDigit = (digits.toInt() / 100) % 10
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