package com.example.ledtimerindicator.CustomViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.ledtimerindicator.R

class LedDisplayView : View {

    private val SIZE_STEPS = 5
    private val FULL_ALPHA = 255
    private val LITTLE_ALPHA = 31
    private val DELTA = 22.5
    private val DOUBLE = 2
    private val QUADRUPLE = 4
    private val CORNER_RADIUS = 10F

    lateinit private var ledPaint: Paint

    private var ledStartX: Float = 0.0F
    private var ledStartY: Float = 0.0F
    private var ledStopX: Float = 0.0F
    private var ledStopY: Float = 0.0F

    private var value = 0

    private var ledColorOn = 0
    private var ledColorOff = 0

    private var ledViewWidthPiece = 0
    private var ledViewHeightPiece = 0

    private var referenceSize = 2
    private var referenceStrokeWidth = 15F

    private var ledViewStrokeWidthPiece = (referenceStrokeWidth / SIZE_STEPS)

    private var isFirstMeasure = true

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
        ledColorOn = ContextCompat.getColor(context, R.color.colorAccent)
        ledColorOff = ContextCompat.getColor(context, R.color.colorAccentDark)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        if (isFirstMeasure) {
            ledViewWidthPiece = width / SIZE_STEPS
            ledViewHeightPiece = height / SIZE_STEPS
            isFirstMeasure = false
        }

        initPaints()
    }

    private fun initPaints() {
        ledPaint = Paint()
        ledPaint.style = Paint.Style.FILL_AND_STROKE
        ledPaint.strokeWidth = referenceStrokeWidth
        ledPaint.alpha = FULL_ALPHA
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        setDrawValue(canvas!!, value)
    }

    fun setScreenValue(value: Int) {
        this.value = value

        invalidate()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setLedOne(canvas: Canvas, isOn: Boolean) {
        ledStartX = ((width / DELTA) * DOUBLE).toFloat()
        ledStartY = ((height / DELTA) * DOUBLE).toFloat()
        ledStopX = ((width / DELTA) * DOUBLE).toFloat()
        ledStopY = ((height / DOUBLE) - (height / DELTA)).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, CORNER_RADIUS, CORNER_RADIUS, ledPaint)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setLedTwo(canvas: Canvas, isOn: Boolean) {
        ledStartX = ((width / DELTA) * QUADRUPLE).toFloat()
        ledStartY = (height / DELTA).toFloat()
        ledStopX = (width.toFloat() - ((width.toFloat() / DELTA) * QUADRUPLE)).toFloat()
        ledStopY = (height / DELTA).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, CORNER_RADIUS, CORNER_RADIUS, ledPaint)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setLedThree(canvas: Canvas, isOn: Boolean) {
        ledStartX = (width.toFloat() - ((width.toFloat() / DELTA) * DOUBLE)).toFloat()
        ledStartY = ((height / DELTA) * DOUBLE).toFloat()
        ledStopX = (width - (width.toFloat() / DELTA) * DOUBLE).toFloat()
        ledStopY = ((height / DOUBLE) - (height / DELTA)).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, CORNER_RADIUS, CORNER_RADIUS, ledPaint)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setLedFour(canvas: Canvas, isOn: Boolean) {
        ledStartX = (height / DELTA).toFloat()
        ledStartY = ((height / DOUBLE) + (height / DELTA)).toFloat()
        ledStopX = (height / DELTA).toFloat()
        ledStopY = (height - ((height / DELTA) * DOUBLE)).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, CORNER_RADIUS, CORNER_RADIUS, ledPaint)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setLedFive(canvas: Canvas, isOn: Boolean) {
        ledStartX = ((width / DELTA) * QUADRUPLE).toFloat()
        ledStartY = (height / DOUBLE).toFloat()
        ledStopX = (width - (width / DELTA) * QUADRUPLE).toFloat()
        ledStopY = (height / DOUBLE).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, CORNER_RADIUS, CORNER_RADIUS, ledPaint)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setLedSix(canvas: Canvas, isOn: Boolean) {
        ledStartX = ((width / DELTA) * QUADRUPLE).toFloat()
        ledStartY = (height - (height / DELTA)).toFloat()
        ledStopX = (width - ((width / DELTA) * QUADRUPLE)).toFloat()
        ledStopY = (height - (height / DELTA)).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, CORNER_RADIUS, CORNER_RADIUS, ledPaint)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setLedSeven(canvas: Canvas, isOn: Boolean) {
        ledStartX = (width - ((width / DELTA) * DOUBLE)).toFloat()
        ledStartY = ((height / DOUBLE) + (height / DELTA)).toFloat()
        ledStopX = (width - ((width / DELTA) * DOUBLE)).toFloat()
        ledStopY = (height - ((height / DELTA) * DOUBLE)).toFloat()

        setLedState(isOn)

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, CORNER_RADIUS, CORNER_RADIUS, ledPaint)
    }

    private fun setLedState(isOn: Boolean) {
        if (isOn) {
            ledPaint.color = ledColorOn
            ledPaint.alpha = FULL_ALPHA
        }
        else {
            ledPaint.color = ledColorOff
            ledPaint.alpha = LITTLE_ALPHA
        }
    }

    fun getLedColor() : Int {
        return ledColorOn
    }

    fun setLedColor(color: Int) {
        ledColorOn = color
        ledColorOff = color

        invalidate()
    }

    fun getLedSize() : Int {
        return referenceSize
    }

    fun setLayoutDimen(size: Int) {
        val layoutParams = this.layoutParams

        if (size > referenceSize) {
            layoutParams.width = layoutParams.width + ledViewWidthPiece
            layoutParams.height = layoutParams.height + ledViewHeightPiece

            referenceStrokeWidth += ledViewStrokeWidthPiece

            this.layoutParams = layoutParams
            referenceSize = size
        } else if (size < referenceSize) {
            layoutParams.width = layoutParams.width - ledViewWidthPiece
            layoutParams.height = layoutParams.height - ledViewHeightPiece

            referenceStrokeWidth -= ledViewStrokeWidthPiece

            this.layoutParams = layoutParams
            referenceSize = size
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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