package com.todokanai.baseproject.abstracts

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow

abstract class MultiSelectRecyclerAdapter<E:Any>(
    itemFlow: Flow<List<E>>,
    private val lifecycleOwner: LifecycleOwner
):BaseRecyclerAdapter<E>(itemFlow,lifecycleOwner) {

    abstract val mode:MutableLiveData<Int>
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

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<E>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.run{
            mode.observe(lifecycleOwner){

            }
        }
    }
}