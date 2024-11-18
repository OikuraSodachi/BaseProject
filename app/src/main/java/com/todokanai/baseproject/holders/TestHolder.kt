package com.todokanai.baseproject.holders

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.todokanai.baseproject.R
import com.todokanai.baseproject.abstracts.BaseRecyclerViewHolder
import com.todokanai.baseproject.data.dataclass.TestHolderItem

class TestHolder(itemView:View): BaseRecyclerViewHolder<TestHolderItem>(itemView) {
    private val testImage = itemView.findViewById<ImageView>(R.id.testImage)
    private val stringText = itemView.findViewById<TextView>(R.id.stringText)
    private val intText = itemView.findViewById<TextView>(R.id.intText)

    override fun onInit(item: TestHolderItem) {
        Glide.with(itemView)
            .load(item.imageUri)
            .into(testImage)
        stringText.text = item.stringData
        intText.text = item.intData.toString()
    }

    override fun onSelected(){
        itemView.setBackgroundColor(Color.RED)
    }

    override fun onUnselected(){
        itemView.setBackgroundColor(0)
    }
}