package com.example.ledtimerindicator

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.example.ledtimerindicator.CustomViews.TimerInputEditText
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.content.Context.INPUT_METHOD_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.view.inputmethod.InputMethodManager
import com.example.ledtimerindicator.CustomViews.LedIndicatorView


class MainActivity : AppCompatActivity() {

    private lateinit var okButton: Button
    private lateinit var timerImput: TimerInputEditText
    private lateinit var firstIndicatorView: LedIndicatorView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        okButton = findViewById(R.id.ok_button)
        timerImput = findViewById(R.id.timer_imput)
        firstIndicatorView = findViewById(R.id.first_led_view)

        initScreen()
    }

    fun initScreen() {
        timerImput.isCursorVisible = false

        timerImput.setOnClickListener {
            if (!timerImput.isCursorVisible)
                timerImput.isCursorVisible = true
                timerImput.requestFocus()

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(timerImput, InputMethodManager.SHOW_IMPLICIT)
        }
    }

}
