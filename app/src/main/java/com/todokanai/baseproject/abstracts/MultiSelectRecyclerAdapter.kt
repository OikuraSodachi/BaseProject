package com.todokanai.baseproject.abstracts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.todokanai.baseproject.interfaces.MultiSelectInterface
import kotlinx.coroutines.flow.Flow

abstract class MultiSelectRecyclerAdapter<E:Any>(
    itemFlow: Flow<List<E>>
):BaseRecyclerAdapter<E>(itemFlow),MultiSelectInterface {

    private var selectedArray = emptyArray<Any>()
    abstract val longClickEnabled : MutableLiveData<Boolean>
    abstract val observer: Observer<Boolean>

    override fun <E : Any> toggleToSelected(selectedItem: E) {
        if(selectedArray.contains(selectedItem)){
            selectedArray = selectedArray.toList().minus(selectedItem).toTypedArray()
        }else{
            selectedArray = selectedArray.plus(selectedItem)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        longClickEnabled.observeForever(observer)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        longClickEnabled.removeObserver(observer)
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<E>, position: Int) {
        val item = itemList[position]
        holder.run{
            onInit(item)
        }
    }
}