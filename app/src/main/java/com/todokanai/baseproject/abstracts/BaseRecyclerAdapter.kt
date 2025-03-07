package com.todokanai.baseproject.abstracts

import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow

/** Base class of [RecyclerView.Adapter]
 *
 * Automatically handles recyclerView Item update
 *  @param itemFlow [Flow] of itemList
 *  **/
abstract class BaseRecyclerAdapter<E:Any>(
    itemFlow: Flow<List<E>>,
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemLiveData = itemFlow.asLiveData()
    var itemList = emptyList<E>()
    private val observer = Observer<List<E>>{
        itemList = it
        notifyDataSetChanged()
    }

    @CallSuper
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        itemLiveData.observeForever(observer)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    /*
    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<E>, position: Int) {
        holder.onInit(itemList[position])
    }

     */

    @CallSuper
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        itemLiveData.removeObserver(observer)
    }
}