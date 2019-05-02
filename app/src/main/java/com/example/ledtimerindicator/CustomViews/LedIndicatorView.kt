package com.example.ledtimerindicator.CustomViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
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

        ledOne.style = Paint.Style.FILL
        ledTwo.style = Paint.Style.FILL
        ledThree.style = Paint.Style.FILL
        ledFour.style = Paint.Style.FILL
        ledFive.style = Paint.Style.FILL
        ledSix.style = Paint.Style.FILL
        ledSeven.style = Paint.Style.FILL

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

        var rect = Rect(100, 500, 600, 900)

        canvas!!.drawRect(rect, ledOne)

        invalidate()
    }



}