package com.todokanai.baseproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todokanai.baseproject.R
import com.todokanai.baseproject.abstracts.AreaSelectAddOn
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
    private val onItemLongClick:(TestHolderItem)->Unit,
):BaseRecyclerAdapter<TestHolderItem>(itemFlow){

    private lateinit var addOn:AreaSelectAddOn

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        addOn = AreaSelectAddOn(
            view = recyclerView,
            onArea = { one,two,three,four ->
                println("tag startX: $one, startY: $two")
                println("tag endX: $three, endY: $four")
            }
        ).apply {
            prepareMotionEventListener()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_recycler,parent,false)
        return TestHolder(view)
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<TestHolderItem>, position: Int) {
        val item = itemList[position]

        val listener = View.OnLongClickListener{
            addOn.startAreaSelection(it)
            false
        }

        holder.run{
            itemView.run{
                setOnClickListener{onItemClick(item)}
                setOnLongClickListener(listener)
            }
        }
    }
}