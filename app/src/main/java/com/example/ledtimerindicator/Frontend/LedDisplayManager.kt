package com.example.ledtimerindicator.Frontend

import android.annotation.SuppressLint
import android.view.View
import com.example.ledtimerindicator.CustomViews.LedDisplayView

class LedDisplayManager(var firstDisplayLed: LedDisplayView, var secondDisplayLed : LedDisplayView, var thirdDisplayLed : LedDisplayView) {

    var firstDigit = 0
    var secondDigit = 0
    var thirdDigit = 0

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
}