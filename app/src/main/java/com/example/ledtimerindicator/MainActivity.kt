package com.example.ledtimerindicator

import android.app.Activity
import android.app.Dialog
import android.graphics.PorterDuff
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.ledtimerindicator.Backend.CountTimerManager
import com.example.ledtimerindicator.Frontend.LedDisplayManager
import com.example.ledtimerindicator.Interfaces.CountTimerListener
import com.madrapps.pikolo.HSLColorPicker
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi


class MainActivity : AppCompatActivity() {

    private val LED_SIZE_MAX_STEPS = 4
    private val LED_SIZE_INCREMENT_DIFF = 1
    private val ZERO_FLAG = 0

    private lateinit var rootView: View
    private lateinit var timerInputContainer: View
    private lateinit var okButton: Button
    private lateinit var startTimerButton: Button
    private lateinit var timerInput: EditText
    private lateinit var ledDisplayManager: LedDisplayManager
    private lateinit var colorChangeButton: ImageButton
    private lateinit var ledSizeChangeButton: ImageButton

    private lateinit var countTimerListener: CountTimerListener

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView = window.decorView.rootView
        timerInputContainer = findViewById(R.id.timer_imput_container)
        okButton = findViewById(R.id.ok_button)
        startTimerButton = findViewById(R.id.start_timer_button)
        timerInput = findViewById(R.id.timer_imput)
        colorChangeButton = findViewById(R.id.button_led_color_change)
        ledSizeChangeButton = findViewById(R.id.button_font_size_change)

        okButton.background = getBackgroundDrawable(getColor(R.color.colorAccent), getResources().getDrawable(R.drawable.button_ripple_effect))
        startTimerButton.background = getBackgroundDrawable(getColor(R.color.colorAccent), getResources().getDrawable(R.drawable.button_ripple_effect))
        timerInput.setTextColor(getColor(R.color.white))

        ledDisplayManager = LedDisplayManager(this)

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
            if (CountTimerManager.getTimeSeconds() >= CountTimerManager.MINIMUM_SECOND) {
                CountTimerManager.start()
            }
        }

        colorChangeButton.setOnClickListener {
            var colorChangeDialog = Dialog(this)
            colorChangeDialog.setContentView(R.layout.color_change_dialog)

            var colorPicker = colorChangeDialog.findViewById<HSLColorPicker>(R.id.color_picker)
            var colorFeedback = colorChangeDialog.findViewById<ImageView>(R.id.color_feedback)

            colorPicker.setColor(ledDisplayManager.getLedsColor())
            colorFeedback.setColorFilter(ledDisplayManager.getLedsColor())

            colorPicker.setColorSelectionListener(object : SimpleColorSelectionListener() {
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun onColorSelected(color: Int) {
                    super.onColorSelected(color)
                    colorFeedback.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
                    ledDisplayManager.setLedsColor(color)

                    var backgroundDrawable = getBackgroundDrawable(color, getResources().getDrawable(R.drawable.button_ripple_effect))
                    okButton.background = backgroundDrawable
                    startTimerButton.background = backgroundDrawable
                }
            })

            colorChangeDialog.show()
        }

        ledSizeChangeButton.setOnClickListener {
            var ledSizeChangeDialog = Dialog(this)
            ledSizeChangeDialog.setContentView(R.layout.led_size_change_dialog)

            var ledSizeSeekBar = ledSizeChangeDialog.findViewById<SeekBar>(R.id.led_size_selector)

            ledSizeSeekBar.max = LED_SIZE_MAX_STEPS
            ledSizeSeekBar.incrementProgressBy(LED_SIZE_INCREMENT_DIFF)
            ledSizeSeekBar.progress = ledDisplayManager.getLedsReferenceSize()

            ledSizeSeekBar.progressDrawable.setColorFilter(ledDisplayManager.getLedsColor(), PorterDuff.Mode.SRC_IN)
            ledSizeSeekBar.thumb.setColorFilter(ledDisplayManager.getLedsColor(), PorterDuff.Mode.SRC_IN)

            ledSizeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    ledDisplayManager.setLedsSize(progress)
                    Log.d("Progress", progress.toString())
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) { }

                override fun onStopTrackingTouch(seekBar: SeekBar?) { }
            })

            ledSizeChangeDialog.show()
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
        imm.hideSoftInputFromWindow(this.rootView.windowToken, ZERO_FLAG)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getBackgroundDrawable(pressedColor: Int, backgroundDrawable: Drawable): RippleDrawable {
        return RippleDrawable(getPressedState(pressedColor), backgroundDrawable, null)
    }

    fun getPressedState(pressedColor: Int): ColorStateList {
        return ColorStateList(arrayOf(intArrayOf()), intArrayOf(pressedColor))
    }

}
