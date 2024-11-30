package com.todokanai.baseproject.abstracts.multiselectrecyclerview

import androidx.recyclerview.selection.SelectionTracker.SelectionObserver

/** callback is called rapidly **/
class BaseSelectionObserver(val callback:()->Unit):SelectionObserver<Long>() {

    override fun onSelectionChanged() {
        super.onSelectionChanged()
        callback()
    }
}