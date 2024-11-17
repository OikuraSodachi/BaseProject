package com.todokanai.baseproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.todokanai.baseproject.R
import com.todokanai.baseproject.abstracts.BaseRecyclerAdapter
import com.todokanai.baseproject.abstracts.BaseRecyclerViewHolder
import com.todokanai.baseproject.data.dataclass.TestHolderItem
import com.todokanai.baseproject.holders.TestHolder
import kotlinx.coroutines.flow.Flow

/**    enable ripple effect on item click (in layout xml)  ->
 *
 *     android:clickable="true"
 *     android:focusable="true"
 *     android:foreground="?attr/selectableItemBackground"
 */

class TestRecyclerAdapter (
    itemFlow: Flow<List<TestHolderItem>>,
    private val onItemClick:(TestHolderItem)->Unit,
    private val onItemLongClick:(TestHolderItem)->Unit
):BaseRecyclerAdapter<TestHolderItem>(itemFlow){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_recycler,parent,false)
        return TestHolder(view)
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<TestHolderItem>, position: Int) {
        val item = itemList[position]
        holder.run{
            onInit(item)
            itemView.run{
                setOnClickListener{onItemClick(item)}
                setOnLongClickListener {
                    onItemLongClick(item)
                    true
                }
            }
        }
    }
}