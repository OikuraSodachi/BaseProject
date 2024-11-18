package com.todokanai.baseproject.abstracts.multiselectrecyclerview

import androidx.recyclerview.selection.SelectionTracker.SelectionObserver

class BaseSelectionObserver<E:Any>(
):SelectionObserver<Long>() {

    override fun onSelectionChanged() {
        super.onSelectionChanged()
    }
}