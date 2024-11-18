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

    var selectedItems:Set<E> = emptySet()

    fun toggleSelection(itemId:Long){
        /// val test = selectionTracker.hasSelection()    //  값이  false로 뜨고있음. 여기부터 해결할 것.
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
                callback = {
                    println("${it}")
                    selectedItems = it.toSet()
                           },
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

    fun getSelected():Set<E>{
        val out = selectionTracker.selection.map{
            itemList[it.toInt()]
        }.toSet()
        return out
    }
    abstract fun selectionEnabled():Boolean
}