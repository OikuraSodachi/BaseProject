package com.todokanai.baseproject.abstracts

import androidx.recyclerview.widget.RecyclerView
import com.todokanai.baseproject.interfaces.MultiSelectInterface
import kotlinx.coroutines.flow.Flow

abstract class MultiSelectRecyclerAdapter<E:Any,VH:RecyclerView.ViewHolder>(
    itemFlow: Flow<List<E>>
):BaseRecyclerAdapter<E,VH>(itemFlow),MultiSelectInterface {

    private var selected = emptyArray<Any>()

    override fun getSelected(): Array<Any> {
        return selected
    }

    override fun <E : Any> toggleToSelected(item: E) {
        if(selected.contains(item)){
            selected = selected.toList().minus(item).toTypedArray()
        }else{
            selected = selected.plus(item)
        }
    }
}