package com.todokanai.baseproject.abstracts.multiselectrecyclerview

import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.SelectionTracker.SelectionObserver

abstract class BaseSelectionObserver<E:Any>(
    private val selectionTracker:SelectionTracker<Long>,
    val itemList:()->List<E>,
    val callback:(items:List<E>)->Unit
):SelectionObserver<Long>() {
    override fun onSelectionChanged() {
        super.onSelectionChanged()
        callback(
            selectionTracker.selection.map{
                itemList()[it.toInt()]
            }
        )
    }
}