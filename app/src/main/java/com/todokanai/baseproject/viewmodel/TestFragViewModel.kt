package com.todokanai.baseproject.viewmodel

import androidx.lifecycle.ViewModel
import com.todokanai.baseproject.data.dataclass.TestHolderItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class TestFragViewModel @Inject constructor() : ViewModel() {

    val list : List<TestHolderItem> = listOf(
        TestHolderItem(null,"stringData",1),
        TestHolderItem(null,"stringData",2),
        TestHolderItem(null,"stringData",3),
        TestHolderItem(null,"stringData",4),
        TestHolderItem(null,"stringData",5),

        TestHolderItem(null,"stringData",6),

        TestHolderItem(null,"stringData",7),

        TestHolderItem(null,"stringData",8),

        TestHolderItem(null,"stringData",9),
        TestHolderItem(null,"stringData",10),
        TestHolderItem(null,"stringData",11),
        TestHolderItem(null,"stringData",12),
        TestHolderItem(null,"stringData",13),
        TestHolderItem(null,"stringData",14),
        TestHolderItem(null,"stringData",15),
        TestHolderItem(null,"stringData",16),
        TestHolderItem(null,"stringData",17),


        )

    val itemFlow : Flow<List<TestHolderItem>> = flowOf(list)

    fun onItemClick(item:TestHolderItem){

    }

    fun onItemLongClick(item:TestHolderItem){

    }
}