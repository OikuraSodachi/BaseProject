package com.todokanai.baseproject.abstracts.multiselectrecyclerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.SelectionTracker.SelectionObserver
import androidx.recyclerview.selection.SelectionTracker.SelectionPredicate
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.todokanai.baseproject.abstracts.BaseRecyclerAdapter
import com.todokanai.baseproject.abstracts.BaseRecyclerViewHolder
import kotlinx.coroutines.flow.Flow

abstract class MultiSelectRecyclerAdapter<E:Any>(
    itemFlow: Flow<List<E>>,
    private val selectionId:String
): BaseRecyclerAdapter<E>(itemFlow) {

    private lateinit var selectionTracker: SelectionTracker<Long>
    abstract val observer: Observer<Int>
    abstract val selectionPredicate:SelectionPredicate<Long>
    abstract val selectionObserver:SelectionObserver<Long>

    // custom variable
    abstract val mode:MutableLiveData<Int>
    //

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        selectionTracker = SelectionTracker.Builder(
            selectionId,
            recyclerView,
            BaseItemKeyProvider(recyclerView),
            BaseItemLookup(recyclerView),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(selectionPredicate)
            .build()

        selectionTracker.addObserver(
            selectionObserver
        )
        
        mode.observeForever(observer)
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<E>, position: Int) {
        super.onBindViewHolder(holder, position)
        val isFileSelected = selectionTracker.selection.contains(position.toLong())
    }

    override fun getItemId(position: Int):Long{
        return position.toLong()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mode.removeObserver(observer)
    }
}