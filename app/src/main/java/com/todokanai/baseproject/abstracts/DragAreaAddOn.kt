package com.todokanai.baseproject.abstracts

import android.view.View

/**
 * Unstable
 *
 * @param view area to apply drag area feature
 *
 * **/
class DragAreaAddOn(
    private val view: View,
    val onArea:(Float,Float,Float,Float)->Unit
):BaseDragAreaAddOn(view) {

    /** drag area 동작 개시시에 **/
    override fun onStartArea(value: Boolean) {
        super.onStartArea(value)
        /** Todo: RecyclerView 등의 경우, scroll 활성화 / 비활성화를 여기서 해야할듯? **/

    }

    /** drag area 동작 도중에 **/
    override fun onMovement(startX: Float, startY: Float, endX: Float, endY: Float){
        // TODO() 대충 범위 이내의 item을 선택된 목록에 누적하여 추가
    }

    /** 시작지점과 끝 지점 좌표 사이의 구간 (Windows의 마우스 클릭-드래그 범위지정에 해당) 를 parameter로서 가져옴 **/
    override fun onGetAreaResult(startX: Float, startY: Float, endX: Float, endY: Float) {
        onGetAreaResult(startX,startY,endX,endY)
    }
}