package com.todokanai.baseproject.abstracts

import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow

/** Handles recyclerView Item update **/
abstract class BaseRecyclerAdapter<E:Any,VH: RecyclerView.ViewHolder>(
    itemFlow: Flow<List<E>>,
): RecyclerView.Adapter<VH>() {
    var itemList = emptyList<E>()
    private val itemLiveData =itemFlow.asLiveData()

    private val observer = Observer<List<E>>{
        itemList = it
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        itemLiveData.observeForever(observer)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        itemLiveData.removeObserver(observer)
    }
}