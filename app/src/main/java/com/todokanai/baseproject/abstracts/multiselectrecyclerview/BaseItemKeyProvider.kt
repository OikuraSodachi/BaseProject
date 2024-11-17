package com.todokanai.baseproject.abstracts.multiselectrecyclerview

import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.widget.RecyclerView
import com.todokanai.baseproject.abstracts.BaseRecyclerViewHolder

class BaseItemKeyProvider(private val recyclerView: RecyclerView): ItemKeyProvider<Long>(SCOPE_MAPPED) {
    override fun getKey(position: Int): Long? {
        val holder = recyclerView.findViewHolderForAdapterPosition(position)
        return holder?.itemId ?: throw IllegalStateException("No Holder")    }

    override fun getPosition(key: Long): Int {
        val holder = recyclerView.findViewHolderForItemId(key)
        return if (holder is BaseRecyclerViewHolder<*>) {
            holder.adapterPosition
        } else {
            RecyclerView.NO_POSITION
        }
    }
}