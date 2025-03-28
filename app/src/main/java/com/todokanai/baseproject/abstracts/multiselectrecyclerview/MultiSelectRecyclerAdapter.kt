package com.todokanai.baseproject.abstracts.multiselectrecyclerview

import android.view.MotionEvent
import androidx.annotation.CallSuper
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.todokanai.baseproject.abstracts.BaseRecyclerAdapter
import kotlinx.coroutines.flow.Flow

/** [BaseRecyclerAdapter] with multi-selection feature
 *
 * key:Long == position:Int .toLong()
 *
 * position:Int == key:Long .toInt()
 * @param itemFlow [Flow] of recyclerview items **/
abstract class MultiSelectRecyclerAdapter<E:Any,VH:RecyclerView.ViewHolder>(
    itemFlow: Flow<List<E>>
): BaseRecyclerAdapter<E,VH>(itemFlow) {
    lateinit var selectionTracker: SelectionTracker<Long>
    abstract val selectionId:String

    fun disableSelection(){
        if(selectionTracker.hasSelection()) {
            selectionTracker.clearSelection()
        }
    }

    @CallSuper
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        selectionBugFix(recyclerView)

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

    /** whether item( itemList[position] ) is selected **/
    fun isSelected(position:Int):Boolean{
        return selectionTracker.selection.contains(position.toLong())
    }

    /** additional SelectionObserver callback (optional) **/
    open fun observerCallback(){}

    /** returns the [Set] of selected Items **/
    fun selectedItems(): Set<E>{
        val out = selectionTracker.selection.map{
            itemList()[it.toInt()]
        }.toSet()
        return out
    }

    /** workaround fix for selection being cleared on touching outside
     *
     * [issueTracker link](https://issuetracker.google.com/issues/177046288#comment7) **/
    private fun selectionBugFix(recyclerView: RecyclerView){
        recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, event: MotionEvent): Boolean {
                val down = event.actionMasked == MotionEvent.ACTION_DOWN
                if(!down || !selectionTracker.hasSelection()) {
                    return false //Don't intercept, otherwise you break scrolling
                }

                val view = recyclerView.findChildViewUnder(event.x, event.y)
                return view == null
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) { }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) { }
        })
    }
}