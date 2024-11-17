package com.todokanai.baseproject.interfaces

interface MultiSelectInterface {

    fun getSelected():Array<Any>

    fun <E:Any> toggleToSelected(item:E)

}