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
    itemFlow: Flow<List<TestHolderItem>>,
):MultiSelectRecyclerAdapter<TestHolderItem>(itemFlow) {

    override var isSelectionEnabled = true

    override val selectionId: String
        get() = "selectionId"

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
                toggleSelection(getItemId(position))
            }
        }
    }

    override fun selectedHolderUI(holder: BaseRecyclerViewHolder<TestHolderItem>,isSelected:Boolean) {
        if(isSelected){
            holder.itemView.setBackgroundColor(Color.GRAY)
        } else{
            holder.itemView.setBackgroundColor(0)
        }
    }
}