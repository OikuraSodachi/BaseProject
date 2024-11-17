package com.todokanai.baseproject.abstracts

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow

/** Handles recyclerView Item update **/
abstract class BaseRecyclerAdapter<E:Any>(
    private val itemFlow: Flow<List<E>>,
    private val lifecycleOwner: LifecycleOwner
): RecyclerView.Adapter<BaseRecyclerViewHolder<E>>() {

    var itemList = emptyList<E>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        itemFlow.asLiveData().observe(lifecycleOwner){
            itemList = it
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<E>, position: Int) {
        holder.onInit(itemList[position])
    }
}