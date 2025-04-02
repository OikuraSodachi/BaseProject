package com.todokanai.baseproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todokanai.baseproject.data.dataclass.TestHolderItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
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

    val itemFlow : StateFlow<List<TestHolderItem>> = flowOf(list).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = list
    )

    fun onItemClick(item:TestHolderItem){

    }

    fun onItemLongClick(item:TestHolderItem){

    }
}