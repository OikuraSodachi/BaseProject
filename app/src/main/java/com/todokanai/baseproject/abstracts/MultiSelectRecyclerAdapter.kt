package com.todokanai.baseproject.abstracts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow

abstract class MultiSelectRecyclerAdapter<E:Any>(
    itemFlow: Flow<List<E>>
):BaseRecyclerAdapter<E>(itemFlow) {

 //   open lateinit var selectionTracker: SelectionTracker<Long>

    abstract val mode:MutableLiveData<Int>
    abstract val observer: Observer<Int>
    private var selectedSet = emptySet<E>()

    fun getSelected():Set<E>{
        return selectedSet
    }

    fun toggleToSelected(selectedItem: E) {
        if(selectedSet.contains(selectedItem)){
            selectedSet = selectedSet.minus(selectedItem)
        }else{
            selectedSet = selectedSet.plus(selectedItem)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)


        mode.observeForever(observer)
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<E>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.run{


        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mode.removeObserver(observer)
    }
}