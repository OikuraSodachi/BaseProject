package com.todokanai.baseproject.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.todokanai.baseproject.R
import com.todokanai.baseproject.abstracts.multiselectrecyclerview.MultiSelectRecyclerAdapter
import com.todokanai.baseproject.data.dataclass.TestHolderItem
import com.todokanai.baseproject.holders.TestHolder

/** example of [MultiSelectRecyclerAdapter] instance **/
class MultiSelectTestAdapter(
    val callback:(selectionEnabled:Boolean)->Unit
): MultiSelectRecyclerAdapter<TestHolderItem,TestHolder>(
    object: DiffUtil.ItemCallback<TestHolderItem>(){
        override fun areItemsTheSame(oldItem: TestHolderItem, newItem: TestHolderItem): Boolean {
            return oldItem.intData == newItem.intData
        }
        override fun areContentsTheSame(oldItem: TestHolderItem, newItem: TestHolderItem): Boolean {
            return oldItem == newItem
        }
    }
) {

    override val selectionId = "selectionId"
    override fun onSelectionChanged(index: Int, item: TestHolderItem) {
        callback(selectionTracker.hasSelection())
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_recycler,parent,false)
        return TestHolder(view)
    }

    override fun onBindViewHolder(holder: TestHolder, position: Int) {
        holder.onInit(getItem(position))
        val isSelected = selectionTracker.selection.contains(position.toLong())
        if(isSelected){
            holder.itemView.setBackgroundColor(Color.GRAY)
        } else{
            holder.itemView.setBackgroundColor(0)
        }
    }

}