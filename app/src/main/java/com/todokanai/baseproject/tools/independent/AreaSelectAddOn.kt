package com.todokanai.baseproject.tools.independent

import android.view.View
import kotlin.properties.Delegates

/**
 * Unstable
 *
 * @param view area to apply areaSelect feature
 *
 * **/
class AreaSelectAddOn(
    private val view: View,
    val onArea:(Float,Float,Float,Float)->Unit
) {

    /** drag 활성화 여부 **/
    private var enabled:Boolean = false
    /** start 지점 초기화 여부 **/
    private var isStartReady = false

    private var startX by Delegates.notNull<Float>()
    private var startY by Delegates.notNull<Float>()

    /** starts area selection (drag selection) **/
    fun startAreaSelection(v: View){
        isStartReady = false
        /** Todo: RecyclerView 등의 경우, scroll 비활성화를 여기서 해야할듯? **/
        v.setOnTouchListener { view, motionEvent ->
            if (isStartReady == false) {
                startX = motionEvent.x
                startY = motionEvent.y
                isStartReady = true
            }
            false
        }
        enabled = true
    }

    /** usage: areaSelection 적용할 view에 setOnTouchListener 등록 **/
    private val onTouchListener = View.OnTouchListener { view, motionEvent ->
        if(enabled == true && motionEvent.action == 1){
            onArea(startX,startY,motionEvent.x,motionEvent.y)
            enabled = false
        }
        false
    }

    /** 시작지점과 끝 지점 좌표 사이의 구간 (Windows의 마우스 클릭-드래그 범위선택에 해당) 를 parameter로서 가져옴 **/
    fun onGetArea(startX: Float, startY: Float, endX: Float, endY: Float) {
        onArea(startX,startY,endX,endY)
    }

    /** motionEvent Listener**/
    fun prepareMotionEventListener(){
        view.setOnTouchListener(onTouchListener)
    }
}