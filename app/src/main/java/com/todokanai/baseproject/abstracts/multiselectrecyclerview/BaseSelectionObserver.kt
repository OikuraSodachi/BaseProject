package com.todokanai.baseproject.abstracts.multiselectrecyclerview

import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.SelectionTracker.SelectionObserver

class BaseSelectionObserver<E:Any>(
    private val selectionTracker:SelectionTracker<Long>,
    val callback:(items:List<E>)->Unit,
    val itemList:()->List<E>
):SelectionObserver<Long>() {

    override fun onSelectionChanged() {
        super.onSelectionChanged()
        val selectedList = selectionTracker.selection.map{
            itemList()[it.toInt()]
        }
        callback(selectedList)
    }
}