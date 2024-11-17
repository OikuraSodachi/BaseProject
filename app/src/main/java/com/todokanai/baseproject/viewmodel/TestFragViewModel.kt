package com.todokanai.baseproject.viewmodel

import androidx.lifecycle.ViewModel
import com.todokanai.baseproject.data.dataclass.TestHolderItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class TestFragViewModel @Inject constructor() : ViewModel() {

    val itemFlow : Flow<List<TestHolderItem>> = flowOf(emptyList<TestHolderItem>())

    fun onItemClick(item:TestHolderItem){

    }

    fun onItemLongClick(item:TestHolderItem){

    }
}