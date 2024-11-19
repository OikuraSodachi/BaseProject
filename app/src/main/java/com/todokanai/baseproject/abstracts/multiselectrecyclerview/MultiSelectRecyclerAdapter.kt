package com.todokanai.baseproject.abstracts.multiselectrecyclerview

import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.todokanai.baseproject.abstracts.BaseRecyclerAdapter
import com.todokanai.baseproject.abstracts.BaseRecyclerViewHolder
import kotlinx.coroutines.flow.Flow

abstract class MultiSelectRecyclerAdapter<E:Any>(
    itemFlow: Flow<List<E>>,
): BaseRecyclerAdapter<E>(itemFlow) {

    abstract val selectionId:String
    private lateinit var selectionTracker: SelectionTracker<Long>

    /** instance of selected items **/
    var selectedItems = emptySet<E>()

    /** select / deSelect Item **/
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

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<E>, position: Int) {
        super.onBindViewHolder(holder, position)
        selectedHolderUI(holder,isSelected(position))
    }

    /** whether if item( itemList[position] )  is selected **/
    fun isSelected(position:Int):Boolean{
        return selectionTracker.selection.contains(position.toLong())
    }

    /** SelectionObserver callback
     *
     * also, setter for selectedItems
     * **/
    open fun observerCallback(items:List<E>){
        this.selectedItems = items.toSet()
    }

    abstract fun selectedHolderUI(holder: BaseRecyclerViewHolder<E>,isSelected:Boolean)
}