package com.example.ledtimerindicator.Backend

import com.example.ledtimerindicator.CustomViews.LedDisplayView

class LedDisplayManager(var firstDisplayLed: LedDisplayView, var secondDisplayLed : LedDisplayView, var thirdDisplayLed : LedDisplayView) {

    var firstDigit = 0
    var secondDigit = 0
    var thirdDigit = 0

    fun setTime(time: String) {
        val digits = time.trim().split("\\s*,\\s*")[0].toInt()

        firstDigit = digits % 10
        secondDigit = (digits / 10) % 10
        thirdDigit = (digits / 100) % 10

        firstDisplayLed.setScreenValue(firstDigit)
        secondDisplayLed.setScreenValue(secondDigit)
        thirdDisplayLed.setScreenValue(thirdDigit)
    }
}