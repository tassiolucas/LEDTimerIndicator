package com.example.ledtimerindicator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.example.ledtimerindicator.CustomViews.TimerInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var okButton: Button
    private lateinit var timerImput: TimerInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        okButton = findViewById(R.id.ok_button)
        timerImput = findViewById(R.id.timer_imput)

        initScreen()
    }

    fun initScreen() {
        timerImput.isCursorVisible = false

        timerImput.setOnClickListener {
            if (!timerImput.isCursorVisible)
                timerImput.isCursorVisible = true
                timerImput.isFocusable = true
        }
    }

}
