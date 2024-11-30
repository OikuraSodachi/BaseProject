package com.todokanai.baseproject.abstracts.multiselectrecyclerview

import androidx.recyclerview.selection.Selection
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.todokanai.baseproject.abstracts.BaseRecyclerAdapter
import com.todokanai.baseproject.abstracts.BaseRecyclerViewHolder
import kotlinx.coroutines.flow.Flow

/** [BaseRecyclerAdapter] with multi-selection feature
 * @param itemFlow [Flow] of recyclerview items
 * **/
abstract class MultiSelectRecyclerAdapter<E:Any>(
    itemFlow: Flow<List<E>>
): BaseRecyclerAdapter<E>(itemFlow) {

    /** selection 기능 활성화 여부 **/
    open var isSelectionEnabled :Boolean = false
    abstract val selectionId:String
    lateinit var selectionTracker: SelectionTracker<Long>

    /** select / deSelect Item **/
    fun toggleSelection(itemId:Long){
        if(isSelectionEnabled) {
            if (selectionTracker.selection.contains(itemId)) {
                selectionTracker.deselect(itemId)
            } else {
                selectionTracker.select(itemId)
            }
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
            BaseSelectionObserver(
                callback = { observerCallback() }
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

    /** whether item( itemList[position] ) is selected **/
    private fun isSelected(position:Int):Boolean{
        return selectionTracker.selection.contains(position.toLong())
    }

    /** SelectionObserver callback
     *
     * also, setter for selectedItems
     * **/
    abstract fun observerCallback()

    /** 선택된 holder에 대한 처리 **/
    abstract fun selectedHolderUI(holder: BaseRecyclerViewHolder<E>,isSelected:Boolean)
}