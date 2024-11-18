package com.todokanai.baseproject.abstracts.multiselectrecyclerview

import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.todokanai.baseproject.abstracts.BaseRecyclerAdapter
import kotlinx.coroutines.flow.Flow

abstract class MultiSelectRecyclerAdapter<E:Any>(
    itemFlow: Flow<List<E>>,
): BaseRecyclerAdapter<E>(itemFlow) {

    abstract val selectionId:String
    private lateinit var selectionTracker: SelectionTracker<Long>

    fun toggleSelection(itemId:Long){
        if (selectionTracker.selection.contains(itemId)) {
            selectionTracker.deselect(itemId)
        } else {
            selectionTracker.select(itemId)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        selectionTracker = SelectionTracker.Builder(
            selectionId,
            recyclerView,
            BaseItemKeyProvider(recyclerView),
            BaseItemLookup(recyclerView),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            BaseSelectionPredicate(recyclerView)
        )
            .build()

        selectionTracker.addObserver(
            BaseSelectionObserver<E>(
                callback = { observerCallback(it) },
                selectionTracker = selectionTracker,
                itemList = {itemList}
            )
        )
    }

    override fun getItemId(position: Int):Long{
        return position.toLong()
    }

    fun isSelected(position:Int):Boolean{
        return selectionTracker.selection.contains(position.toLong())
    }

    /** 선택된 Item 목록 **/
    open fun getSelectedItems():Set<E>{
        val out = selectionTracker.selection.map{
            itemList[it.toInt()]
        }.toSet()
        return out
    }

    abstract fun observerCallback(selectedItems:List<E>)

    /** selection 활성화 여부 **/
    abstract fun selectionEnabled():Boolean
}