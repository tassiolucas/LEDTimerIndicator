package com.example.ledtimerindicator.CustomViews

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import com.example.ledtimerindicator.R
import java.security.Key

class TimerInputEditText : EditText {

    constructor(context: Context) : super(context) { }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) { }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {
        if (event!!.keyCode == KeyEvent.KEYCODE_BACK && event!!.action == KeyEvent.ACTION_UP)
            this.clearFocus()

        return super.onKeyPreIme(keyCode, event)
    }
}