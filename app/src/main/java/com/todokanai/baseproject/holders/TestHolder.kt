package com.todokanai.baseproject.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.todokanai.baseproject.R
import com.todokanai.baseproject.data.dataclass.TestHolderItem

class TestHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    private val testImage = itemView.findViewById<ImageView>(R.id.testImage)
    private val stringText = itemView.findViewById<TextView>(R.id.stringText)
    private val intText = itemView.findViewById<TextView>(R.id.intText)

    fun onInit(item: TestHolderItem) {
        item.imageUri?.let {
            Glide.with(itemView)
                .load(it)
                .into(testImage)
        }
        stringText.text = item.stringData
        intText.text = item.intData.toString()
    }
}