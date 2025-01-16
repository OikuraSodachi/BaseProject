package com.todokanai.baseproject.abstracts.multiselectrecyclerview

import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.todokanai.baseproject.abstracts.BaseRecyclerViewHolder

/** Base class of [SelectionTracker.SelectionPredicate] for [MultiSelectRecyclerAdapter] **/
class BaseSelectionPredicate(private val recyclerView: RecyclerView) : SelectionTracker.SelectionPredicate<Long>() {
    override fun canSetStateForKey(key: Long, nextState: Boolean): Boolean {
        val holder = recyclerView.findViewHolderForItemId(key)
        return if (holder is BaseRecyclerViewHolder<*>) {
            true
        } else {
            false
        }
    }

    override fun canSetStateAtPosition(position: Int, nextState: Boolean): Boolean {
        return true
    }

    override fun canSelectMultiple(): Boolean {
        return true
    }
}