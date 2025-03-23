package com.todokanai.baseproject.viewmodel

import androidx.lifecycle.ViewModel
import com.todokanai.baseproject.data.dataclass.TestHolderItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class MultiSelectViewModel @Inject constructor() : ViewModel() {
    val list : List<TestHolderItem> = temp()
    private fun temp():List<TestHolderItem>{
        val out = mutableListOf<TestHolderItem>()
        for(i in 1..100){
            out.add(TestHolderItem(null,"string$i",i))
        }
        return out
    }

    val itemFlow : Flow<List<TestHolderItem>> = flowOf(list)

    fun onItemClick(item:TestHolderItem){

    }

    fun onItemLongClick(item:TestHolderItem){

    }
}