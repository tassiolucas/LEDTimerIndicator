package com.example.ledtimerindicator

import android.app.Activity
import android.app.Dialog
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.ledtimerindicator.Backend.CountTimerManager
import com.example.ledtimerindicator.CustomViews.LedDisplayView
import com.example.ledtimerindicator.Frontend.LedDisplayManager
import com.example.ledtimerindicator.Interfaces.CountTimerListener
import com.madrapps.pikolo.HSLColorPicker
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener

class MainActivity : AppCompatActivity() {

    private lateinit var rootView: View
    private lateinit var timerInputContainer: View
    private lateinit var okButton: Button
    private lateinit var startTimerButton: Button
    private lateinit var timerInput: EditText
    private lateinit var ledDisplayManager: LedDisplayManager
    private lateinit var firstDisplayLed: LedDisplayView
    private lateinit var secondDisplayLed: LedDisplayView
    private lateinit var thirdDisplayLed: LedDisplayView
    private lateinit var colorChangeButton: ImageButton
    private lateinit var ledSizeChangeButton: ImageButton

    private lateinit var countTimerListener: CountTimerListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView = window.decorView.rootView
        timerInputContainer = findViewById(R.id.timer_imput_container)
        okButton = findViewById(R.id.ok_button)
        startTimerButton = findViewById(R.id.start_timer_button)
        timerInput = findViewById(R.id.timer_imput)
        firstDisplayLed = findViewById(R.id.first_led_view)
        secondDisplayLed = findViewById(R.id.second_led_view)
        thirdDisplayLed = findViewById(R.id.third_led_view)
        colorChangeButton = findViewById(R.id.button_led_color_change)
        ledSizeChangeButton = findViewById(R.id.button_font_size_change)

        ledDisplayManager =
            LedDisplayManager(firstDisplayLed, secondDisplayLed, thirdDisplayLed)

        countTimerListener = object : CountTimerListener {
            override fun onStartCount() {
                startTimerButton.setText(R.string.timer_in_progress)
                startTimerButton.isEnabled = false
                startTimerButton.setTextColor(resources.getColor(R.color.hint_imput))
            }

            override fun onTickCount(seconds: Long) {
                ledDisplayManager.setTime(seconds.toString())
            }

            override fun onFinish() {
                startTimerButton.setText(R.string.start_timer_label)
                startTimerButton.isEnabled = true
                startTimerButton.setTextColor(resources.getColor(R.color.white))
            }
        }

        CountTimerManager.init(countTimerListener)

        initScreen()
    }

    fun initScreen() {
        timerInput.setOnClickListener {
            if (timerInput.isCursorVisible) {
                hideKeyboard(this)
            }

            timerInput.inputType = InputType.TYPE_CLASS_NUMBER
            timerInputContainer.requestFocus()
        }

        timerInput.setOnEditorActionListener( TextView.OnEditorActionListener { textView, id, keyEvent ->
            timerInput.inputType = InputType.TYPE_CLASS_NUMBER

            if (id == EditorInfo.IME_ACTION_DONE) {
                setTimerCount(timerInput.text.toString())
                return@OnEditorActionListener true
            }

            return@OnEditorActionListener false
        })

        okButton.setOnClickListener {
            setTimerCount(timerInput.text.toString())
            hideKeyboard(this)
        }

        startTimerButton.setOnClickListener {
            if (CountTimerManager.getTimeSeconds() >= 1) {
                CountTimerManager.start()
            }
        }

        colorChangeButton.setOnClickListener {
            var colorDialog = Dialog(this)
            colorDialog.setContentView(R.layout.color_change_dialog)
            colorDialog.setTitle(R.string.color_picker_title)

            var colorPicker = colorDialog.findViewById<HSLColorPicker>(R.id.color_picker)
            var colorFeedback = colorDialog.findViewById<ImageView>(R.id.color_feedback)

            colorPicker.setColorSelectionListener(object : SimpleColorSelectionListener() {
                override fun onColorSelected(color: Int) {
                    super.onColorSelected(color)
                    colorFeedback.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
                }
            })

            colorDialog.show()
        }
    }

    private fun setTimerCount(timerInput: String) {
        if (!timerInput.isEmpty()) {
            ledDisplayManager.setTime(timerInput)
            CountTimerManager.setTimeSeconds(timerInput.toLong())
        }
    }

    private fun hideKeyboard(activity: Activity) {
        var imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.rootView.windowToken, 0)
    }
}
