package com.example.ledtimerindicator.CustomViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.example.ledtimerindicator.R

class LedDisplayView : View {

    lateinit private var ledPaint: Paint
    lateinit private var ledTwo: Paint
    lateinit private var ledThree: Paint
    lateinit private var ledFour: Paint
    lateinit private var ledFive: Paint
    lateinit private var ledSix: Paint
    lateinit private var ledSeven: Paint

    private var ledStartX: Float = 0.0F
    private var ledStartY: Float = 0.0F
    private var ledStopX: Float = 0.0F
    private var ledStopY: Float = 0.0F

    private var value = 0

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context)
    }

    private fun init(context: Context) {
        invalidate()

        this.setBackgroundColor(resources.getColor(R.color.hint_imput))
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        initPaints(width.toFloat(), height.toFloat())
    }

    private fun initPaints(widthMeasure: Float, heightMeasure: Float) {
        ledPaint = Paint()
        ledPaint.style = Paint.Style.FILL_AND_STROKE
        ledPaint.strokeWidth = 25F
        ledPaint.alpha = 255
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        setDrawValue(canvas!!, value)
    }

    fun setScreenValue(value: Int) {
        this.value = value

        invalidate()
    }

    private fun setLedOne(canvas: Canvas, isOn: Boolean) {
        ledStartX = ((width / 22.5) * 2).toFloat()
        ledStartY = ((height / 22.5) * 2).toFloat()
        ledStopX = ((width / 22.5) * 2).toFloat()
        ledStopY = ((height / 2) - (height / 22.5)).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledPaint)
    }

    private fun setLedTwo(canvas: Canvas, isOn: Boolean) {
        ledStartX = ((width / 22.5) * 4).toFloat()
        ledStartY = (height / 22.5).toFloat()
        ledStopX = (width.toFloat() - ((width.toFloat() / 22.5) * 4)).toFloat()
        ledStopY = (height / 22.5).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledPaint)
    }

    private fun setLedThree(canvas: Canvas, isOn: Boolean) {
        ledStartX = (width.toFloat() - ((width.toFloat() / 22.5) * 2)).toFloat()
        ledStartY = ((height / 22.5) * 2).toFloat()
        ledStopX = (width - (width.toFloat() / 22.5) * 2).toFloat()
        ledStopY = ((height / 2) - (height / 22.5)).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledPaint)
    }

    private fun setLedFour(canvas: Canvas, isOn: Boolean) {
        ledStartX = (height / 22.5).toFloat()
        ledStartY = ((height / 2) + (height / 22.5)).toFloat()
        ledStopX = (height / 22.5).toFloat()
        ledStopY = (height - ((height / 22.5) * 2)).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledPaint)
    }

    private fun setLedFive(canvas: Canvas, isOn: Boolean) {
        ledStartX = ((width / 22.5) * 4).toFloat()
        ledStartY = (height / 2).toFloat()
        ledStopX = (width - (width / 22.5) * 4).toFloat()
        ledStopY = (height / 2).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledPaint)
    }

    private fun setLedSix(canvas: Canvas, isOn: Boolean) {
        ledStartX = ((width / 22.5) * 4).toFloat()
        ledStartY = (height - (height / 22.5)).toFloat()
        ledStopX = (width - ((width / 22.5) * 4)).toFloat()
        ledStopY = (height - (height / 22.5)).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledPaint)
    }

    private fun setLedSeven(canvas: Canvas, isOn: Boolean) {
        ledStartX = (width - ((width / 22.5) * 2)).toFloat()
        ledStartY = ((height / 2) + (height / 22.5)).toFloat()
        ledStopX = (width - ((width / 22.5) * 2)).toFloat()
        ledStopY = (height - ((height / 22.5) * 2)).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledPaint)
    }

    private fun setLedState(isOn: Boolean) {
        if (isOn) {
            ledPaint.color = ContextCompat.getColor(context, R.color.colorAccent)
            ledPaint.alpha = 255
        }
        else {
            ledPaint.color = ContextCompat.getColor(context, R.color.colorAccentDark)
            ledPaint.alpha = 127
        }
    }

    private fun setDrawValue(canvas: Canvas, value: Int) {
        when(value) {
            0 -> {
                setLedOne(canvas, true)
                setLedTwo(canvas, true)
                setLedThree(canvas, true)
                setLedFour(canvas, true)
                setLedFive(canvas, false)
                setLedSix(canvas, true)
                setLedSeven(canvas, true)
            }
            1 -> {
                setLedOne(canvas, false)
                setLedTwo(canvas, false)
                setLedThree(canvas, true)
                setLedFour(canvas, false)
                setLedFive(canvas, false)
                setLedSix(canvas, false)
                setLedSeven(canvas, true)
            }
            2 -> {
                setLedOne(canvas, false)
                setLedTwo(canvas, true)
                setLedThree(canvas, true)
                setLedFour(canvas, true)
                setLedFive(canvas, true)
                setLedSix(canvas, true)
                setLedSeven(canvas, false)
            }
            3 -> {
                setLedOne(canvas, false)
                setLedTwo(canvas, true)
                setLedThree(canvas, true)
                setLedFour(canvas, false)
                setLedFive(canvas, true)
                setLedSix(canvas, true)
                setLedSeven(canvas, true)
            }
            4 -> {
                setLedOne(canvas, true)
                setLedTwo(canvas, false)
                setLedThree(canvas, true)
                setLedFour(canvas, false)
                setLedFive(canvas, true)
                setLedSix(canvas, false)
                setLedSeven(canvas, true)
            }
            5 -> {
                setLedOne(canvas, true)
                setLedTwo(canvas, true)
                setLedThree(canvas, false)
                setLedFour(canvas, false)
                setLedFive(canvas, true)
                setLedSix(canvas, true)
                setLedSeven(canvas, true)
            }
            6 -> {
                setLedOne(canvas, true)
                setLedTwo(canvas, true)
                setLedThree(canvas, false)
                setLedFour(canvas, true)
                setLedFive(canvas, true)
                setLedSix(canvas, true)
                setLedSeven(canvas, true)
            }
            7 -> {
                setLedOne(canvas, false)
                setLedTwo(canvas, true)
                setLedThree(canvas, true)
                setLedFour(canvas, false)
                setLedFive(canvas, false)
                setLedSix(canvas, false)
                setLedSeven(canvas, true)
            }
            8 -> {
                setLedOne(canvas, true)
                setLedTwo(canvas, true)
                setLedThree(canvas, true)
                setLedFour(canvas, true)
                setLedFive(canvas, true)
                setLedSix(canvas, true)
                setLedSeven(canvas, true)
            }
            9 -> {
                setLedOne(canvas, true)
                setLedTwo(canvas, true)
                setLedThree(canvas, true)
                setLedFour(canvas, false)
                setLedFive(canvas, true)
                setLedSix(canvas, true)
                setLedSeven(canvas, true)
            }
        }
    }
}