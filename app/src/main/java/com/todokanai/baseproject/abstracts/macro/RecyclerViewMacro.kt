package com.todokanai.baseproject.abstracts.macro

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow

/**
 * @param recyclerView RecyclerView instance
 * @param itemFlow [Flow] of itemList
 * **/
abstract class RecyclerViewMacro(
    private val recyclerView: RecyclerView,
    private val lManger:LinearLayoutManager,
    private val decoration: RecyclerView.ItemDecoration? = null,
    private val itemFlow: Flow<List<Any>>
) {
    fun applyMacro(
        adapter:RecyclerView.Adapter<RecyclerView.ViewHolder>
    ){
        recyclerView.run{
            this.adapter = adapter
            this.layoutManager = lManger
            decoration?.let{
                addItemDecoration(it)
            }
        }
    }
}