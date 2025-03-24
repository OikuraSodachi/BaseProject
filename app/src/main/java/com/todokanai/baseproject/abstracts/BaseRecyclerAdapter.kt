package com.todokanai.baseproject.abstracts

import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow

/** Base class of [RecyclerView.Adapter]
 *
 * Automatically handles recyclerView Item update
 *  @param itemFlow [Flow] of itemList **/
abstract class BaseRecyclerAdapter<E:Any>(
    itemFlow: Flow<List<E>>,
): RecyclerView.Adapter<BaseRecyclerViewHolder<E>>() {

    private val itemLiveData = itemFlow.asLiveData()
    var itemList = emptyList<E>()
    private val observer = Observer<List<E>>{
        itemList = it
        refreshItemList(it)
    }

    /** oldList 와 newList 를 비교해서 dataSetChanged 적용 **/
    private fun refreshItemList(newList:List<E>){
        val diffResult = DiffUtil.calculateDiff(BaseRecyclerDiffUtil(itemList, newList))
        itemList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    @CallSuper
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        itemLiveData.observeForever(observer)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @CallSuper
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        itemLiveData.removeObserver(observer)
    }

    abstract fun areItemsSame(oldItem:E, newItem:E):Boolean

    inner class BaseRecyclerDiffUtil(
        private val oldList:List<E>,
        private val newList:List<E>
    ): DiffUtil.Callback(){
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return areItemsSame(oldList[oldItemPosition],newList[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

}