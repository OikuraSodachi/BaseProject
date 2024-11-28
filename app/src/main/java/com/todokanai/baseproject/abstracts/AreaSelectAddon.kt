package com.todokanai.baseproject.abstracts

import android.view.View

/**
 * @param view [View] to apply areaSelect feature
 *
 * **/
class AreaSelectAddon(
    private val view: View,
    val onGetArea:(Float,Float,Float,Float)->Unit
) {
    /** drag 활성화 여부 **/
    private var enabled:Boolean = false

    private var isStartReady = false
    private var startX : Float = 0f
    private var startY : Float = 0f

    val longClickListener = View.OnLongClickListener {
        isStartReady = false
        it.setOnTouchListener { view, motionEvent ->
            if (isStartReady == false) {
                startX = motionEvent.x
                startY = motionEvent.y
                isStartReady = true
            }
            false
        }
        enabled = true
        false
    }

    val onTouchListener = View.OnTouchListener { view, motionEvent ->
        if(enabled == true && motionEvent.action == 1){

            onGetArea(startX,startY,motionEvent.x,motionEvent.y)

            enabled = false
        }
        false

    }

    fun add(){
        view.run{
            setOnLongClickListener(longClickListener)
            setOnTouchListener(onTouchListener)
        }
    }
}