package com.example.ledtimerindicator

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.example.ledtimerindicator.CustomViews.TimerInputEditText
import android.view.inputmethod.InputMethodManager
import com.example.ledtimerindicator.Backend.CountTimerManager
import com.example.ledtimerindicator.Backend.LedDisplayManager
import com.example.ledtimerindicator.CustomViews.LedDisplayView
import com.example.ledtimerindicator.Interfaces.CountTimerListener


class MainActivity : AppCompatActivity() {

    private lateinit var okButton: Button
    private lateinit var startTimerButton: Button
    private lateinit var timerInput: TimerInputEditText
    private lateinit var ledDisplayManager: LedDisplayManager
    private lateinit var firstDisplayLed: LedDisplayView
    private lateinit var secondDisplayLed: LedDisplayView
    private lateinit var thirdDisplayLed: LedDisplayView

    private lateinit var countTimerListener: CountTimerListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        okButton = findViewById(R.id.ok_button)
        startTimerButton = findViewById(R.id.start_timer_button)
        timerInput = findViewById(R.id.timer_imput)
        firstDisplayLed = findViewById(R.id.first_led_view)
        secondDisplayLed = findViewById(R.id.second_led_view)
        thirdDisplayLed = findViewById(R.id.third_led_view)

        ledDisplayManager = LedDisplayManager(firstDisplayLed, secondDisplayLed, thirdDisplayLed)

        countTimerListener = object : CountTimerListener {
            override fun onSetTime(seconds: Long) {
            }

            override fun onStartCount() {
                CountTimerManager.start()
            }

            override fun onTickCount(seconds: Long) {
                ledDisplayManager.setTime(seconds.toString())
            }

            override fun onFinish() {

            }
        }

        CountTimerManager.init(countTimerListener)

        initScreen()
    }

    fun initScreen() {
        timerInput.isCursorVisible = false

        timerInput.setOnClickListener {
            if (!timerInput.isCursorVisible)
                timerInput.isCursorVisible = true
                timerInput.requestFocus()

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(timerInput, InputMethodManager.SHOW_IMPLICIT)
        }

        okButton.setOnClickListener {
            if (!timerInput.text.isEmpty()) {
                ledDisplayManager.setTime(timerInput.text.toString())
                CountTimerManager.setTimeSeconds(timerInput.text.toString().toLong())
            }
        }

        startTimerButton.setOnClickListener {
            countTimerListener.onStartCount()
        }
    }

}
