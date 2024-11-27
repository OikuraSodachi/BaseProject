package com.todokanai.baseproject.abstracts

import android.view.View

class AreaSelectAddon(val view: View) {
    /** drag 활성화 여부 **/
    var enabled:Boolean = false

    var isStartReady = false
    var startX : Float = 0f
    var startY : Float = 0f

    val longClickListener = View.OnLongClickListener {
        isStartReady = false
        it.setOnTouchListener { view, motionEvent ->
            if (isStartReady == false) {
                startX = motionEvent.x
                startY = motionEvent.y
                println("tag longClick x:${motionEvent.x}, y:${motionEvent.y}")
                isStartReady = true
            }
            false
        }
        enabled = true
        false
    }

    val onTouchListener = View.OnTouchListener { view, motionEvent ->
        if( enabled ) {
            println("x:${motionEvent.x}, y:${motionEvent.y}, action: ${motionEvent.action}")
            //  onMovement(motionEvent.x,motionEvent.y)

        }
        if(enabled == true && motionEvent.action == 1){
            //  wManager.removeView(area)

            println("tag startX: $startX, startY: $startY")
            println("tag endX: ${motionEvent.x}, endY: ${motionEvent.y}")

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