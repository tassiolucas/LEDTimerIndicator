package com.example.ledtimerindicator.CustomViews

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import com.example.ledtimerindicator.R

class TimerInputEditText : EditText {

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
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyPreIme(keyCode, event)
    }
}