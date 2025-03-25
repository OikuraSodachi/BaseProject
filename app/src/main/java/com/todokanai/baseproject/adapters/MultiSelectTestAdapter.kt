package com.todokanai.baseproject.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todokanai.baseproject.R
import com.todokanai.baseproject.abstracts.multiselectrecyclerview.MultiSelectRecyclerAdapter
import com.todokanai.baseproject.data.dataclass.TestHolderItem
import com.todokanai.baseproject.holders.TestHolder
import kotlinx.coroutines.flow.Flow

/** example of [MultiSelectRecyclerAdapter] instance **/
class MultiSelectTestAdapter(
    itemFlow: Flow<List<TestHolderItem>>,
    val callback:(selectionEnabled:Boolean)->Unit
): MultiSelectRecyclerAdapter<TestHolderItem,TestHolder>(itemFlow) {

    override val selectionId = "selectionId"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_recycler,parent,false)
        return TestHolder(view)
    }

    override fun onSelectionChanged(
        holder: RecyclerView.ViewHolder,
        isSelected: Boolean
    ){
        if(isSelected){
            holder.itemView.setBackgroundColor(Color.GRAY)
        } else{
            holder.itemView.setBackgroundColor(0)
        }
    }

    override fun observerCallback() {
        super.observerCallback()
        callback(selectionTracker.hasSelection())
    }

    override fun areItemsSame(oldItem: TestHolderItem, newItem: TestHolderItem): Boolean {
        return oldItem.intData == newItem.intData
    }

}