package com.example.ledtimerindicator.CustomViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.example.ledtimerindicator.R

class LedIndicatorView : View {

    lateinit var ledOne: Paint
    lateinit var ledTwo: Paint
    lateinit var ledThree: Paint
    lateinit var ledFour: Paint
    lateinit var ledFive: Paint
    lateinit var ledSix: Paint
    lateinit var ledSeven: Paint

    var ledStartX: Float = 0.0F
    var ledStartY: Float = 0.0F
    var ledStopX: Float = 0.0F
    var ledStopY: Float = 0.0F

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

        invalidate()
    }

    private fun initPaints(widthMeasure: Float, heightMeasure: Float) {
        ledOne = Paint()
        ledTwo = Paint()
        ledThree = Paint()
        ledFour = Paint()
        ledFive = Paint()
        ledSix = Paint()
        ledSeven = Paint()

        ledOne.style = Paint.Style.FILL_AND_STROKE
        ledTwo.style = Paint.Style.FILL_AND_STROKE
        ledThree.style = Paint.Style.FILL_AND_STROKE
        ledFour.style = Paint.Style.FILL_AND_STROKE
        ledFive.style = Paint.Style.FILL_AND_STROKE
        ledSix.style = Paint.Style.FILL_AND_STROKE
        ledSeven.style = Paint.Style.FILL_AND_STROKE

        ledOne.strokeWidth = 25F

        ledOne.alpha = 255
        ledTwo.alpha = 255
        ledThree.alpha = 255
        ledFour.alpha = 255
        ledFive.alpha = 255
        ledSix.alpha = 255
        ledSeven.alpha = 255

        ledOne.color = ContextCompat.getColor(context, R.color.colorAccent)
        ledTwo.color = ContextCompat.getColor(context, R.color.colorAccent)
        ledThree.color = ContextCompat.getColor(context, R.color.colorAccent)
        ledFour.color = ContextCompat.getColor(context, R.color.colorAccent)
        ledFive.color = ContextCompat.getColor(context, R.color.colorAccent)
        ledSix.color = ContextCompat.getColor(context, R.color.colorAccent)
        ledSeven.color = ContextCompat.getColor(context, R.color.colorAccent)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        turnLedOne(canvas!!, true)
        turnLedTwo(canvas!!, true)
        turnLedThree(canvas!!, true)
        turnLedFour(canvas!!, true)
        turnLedFive(canvas!!, true)
        turnLedSix(canvas!!, true)
        turnLedSeven(canvas!!, true)

        invalidate()
    }

    fun turnLedOne(canvas: Canvas, isOn: Boolean) {
        ledStartX = ((width / 22.5) * 2).toFloat()
        ledStartY = ((height / 22.5) * 2).toFloat()
        ledStopX = ((width / 22.5) * 2).toFloat()
        ledStopY = ((height / 2) - (height / 22.5)).toFloat()

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledOne)
    }

    fun turnLedTwo(canvas: Canvas, isOn: Boolean) {
        ledStartX = ((width / 22.5) * 4).toFloat()
        ledStartY = (height / 22.5).toFloat()
        ledStopX = (width.toFloat() - ((width.toFloat() / 22.5) * 4)).toFloat()
        ledStopY = (height / 22.5).toFloat()

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledOne)
    }

    fun turnLedThree(canvas: Canvas, isOn: Boolean) {
        ledStartX = (width.toFloat() - ((width.toFloat() / 22.5) * 2)).toFloat()
        ledStartY = ((height / 22.5) * 2).toFloat()
        ledStopX = (width - (width.toFloat() / 22.5) * 2).toFloat()
        ledStopY = ((height / 2) - (height / 22.5)).toFloat()

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledOne)
    }

    fun turnLedFour(canvas: Canvas, isOn: Boolean) {
        ledStartX = (height / 22.5).toFloat() // ((height / 2) + (height / 22.5)).toFloat()
        ledStartY = ((height / 2) + (height / 22.5)).toFloat()
        ledStopX = (height / 22.5).toFloat()
        ledStopY = (height - ((height / 22.5) * 2)).toFloat()

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledOne)
    }

    fun turnLedFive(canvas: Canvas, isOn: Boolean) {
        ledStartX = ((width / 22.5) * 4).toFloat()
        ledStartY = (height / 2).toFloat()
        ledStopX = (width - (width / 22.5) * 4).toFloat()
        ledStopY = (height / 2).toFloat()

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledOne)
    }

    fun turnLedSix(canvas: Canvas, isOn: Boolean) {
        ledStartX = ((width / 22.5) * 4).toFloat()
        ledStartY = (height - (height / 22.5)).toFloat()
        ledStopX = (width - ((width / 22.5) * 4)).toFloat()
        ledStopY = (height - (height / 22.5)).toFloat()

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledOne)
    }

    fun turnLedSeven(canvas: Canvas, isOn: Boolean) {
        ledStartX = (width - ((width / 22.5) * 2)).toFloat()
        ledStartY = ((height / 2) + (height / 22.5)).toFloat()
        ledStopX = (width - ((width / 22.5) * 2)).toFloat()
        ledStopY = (height - ((height / 22.5) * 2)).toFloat()

        canvas.drawRoundRect(ledStartX, ledStartY, ledStopX, ledStopY, 10F, 10F, ledOne)
    }




}