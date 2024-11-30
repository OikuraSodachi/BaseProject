package com.todokanai.baseproject.holders

import android.view.View
import com.todokanai.baseproject.abstracts.BaseRecyclerViewHolder

abstract class BaseMultiSelectRecyclerViewHolder<E:Any>(view: View):BaseRecyclerViewHolder<E>(view) {
    abstract fun onToggleSelected(isSelected:Boolean)
}