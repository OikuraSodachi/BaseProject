package com.todokanai.baseproject.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.todokanai.baseproject.R
import com.todokanai.baseproject.abstracts.BaseRecyclerViewHolder
import com.todokanai.baseproject.abstracts.multiselectrecyclerview.MultiSelectRecyclerAdapter
import com.todokanai.baseproject.data.dataclass.TestHolderItem
import com.todokanai.baseproject.holders.TestHolder
import kotlinx.coroutines.flow.Flow

/** example of [MultiSelectRecyclerAdapter] instance **/
class MultiSelectTestAdapter(
    itemFlow: Flow<List<TestHolderItem>>
):MultiSelectRecyclerAdapter<TestHolderItem>(itemFlow) {

    override val selectionId = "selectionId"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<TestHolderItem> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_recycler,parent,false)
        return TestHolder(view)
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<TestHolderItem>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.run{
            setOnClickListener {
                updateToSelection(position)
            }
        }
    }

    override fun onSelectionChanged(
        holder: BaseRecyclerViewHolder<TestHolderItem>,
        isSelected: Boolean
    ){
        if(isSelected){
            holder.itemView.setBackgroundColor(Color.GRAY)
        } else{
            holder.itemView.setBackgroundColor(0)
        }
    }

    /*
    override fun observerCallback() {
        /** position of item (starts from 0 ) **/
        val position = selectionTracker.selection.map{it.toInt()}
        println("observerCallback: $position")
    }
     */
}