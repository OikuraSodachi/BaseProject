package com.todokanai.basemodule.abstracts

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder<E:Any>(view: View):RecyclerView.ViewHolder(view) {

    abstract fun onInit(item:E)
}