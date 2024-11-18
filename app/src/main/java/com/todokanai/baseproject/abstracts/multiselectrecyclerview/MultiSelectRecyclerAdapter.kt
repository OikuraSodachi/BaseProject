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

    // custom variable
   // abstract val mode:MutableLiveData<Int>
    //

    fun toggleSelection(
        itemId:Long,
        onSelected:()->Unit,
        onUnselected:()->Unit
    ){
        /// val test = selectionTracker.hasSelection()    //  값이  false로 뜨고있음. 여기부터 해결할 것.
        if(selectionTracker.selection.contains(itemId)) {
            selectionTracker.deselect(itemId)
            println("selection: ${selectionTracker.selection.size()}")
            onUnselected()
        }else{
            selectionTracker.select(itemId)
            println("selection: ${selectionTracker.selection.size()}")

            onSelected()
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
            BaseSelectionObserver<E>()
        )
     //   mode.observeForever(observer)
    }

    override fun getItemId(position: Int):Long{
        return position.toLong()
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<E>, position: Int) {
        super.onBindViewHolder(holder, position)
        if(isSelected(position)){
            holder.onSelected()
        }else{
            holder.onUnselected()
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
       // mode.removeObserver(observer)
    }

    fun getSelected():Set<E>{
        val out = selectionTracker.selection.map{
            itemList[it.toInt()]
        }.toSet()
        return out
    }

    fun isSelected(itemPosition:Int):Boolean{
        return selectionTracker.selection.contains(itemPosition.toLong())
    }
}