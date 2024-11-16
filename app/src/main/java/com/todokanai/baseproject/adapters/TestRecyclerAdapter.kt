package com.todokanai.baseproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todokanai.baseproject.R
import com.todokanai.baseproject.data.dataclass.TestHolderItem
import com.todokanai.baseproject.holders.TestHolder

/**    enable ripple effect on item click (in layout xml)  ->
 *
 *     android:clickable="true"
 *     android:focusable="true"
 *     android:foreground="?attr/selectableItemBackground"
 */

class TestRecyclerAdapter (
    private val onItemClick:(TestHolderItem)->Unit,
    private val onItemLongClick:(TestHolderItem)->Unit
    ):RecyclerView.Adapter<TestHolder>(){

    var itemList =emptyList<TestHolderItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_recycler,parent,false)
        return TestHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: TestHolder, position: Int) {
        val item = itemList[position]

        holder.run{
            setData(item)
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