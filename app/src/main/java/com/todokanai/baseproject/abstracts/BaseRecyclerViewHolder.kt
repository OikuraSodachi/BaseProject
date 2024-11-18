package com.todokanai.baseproject.abstracts

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseRecyclerViewHolder<E:Any>(val view: View):RecyclerView.ViewHolder(view) {

    open fun onInit(item:E){

    }
}