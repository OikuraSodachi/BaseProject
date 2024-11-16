package com.todokanai.baseproject.viewmodel

import androidx.lifecycle.ViewModel
import com.todokanai.baseproject.data.dataclass.TestHolderItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestFragViewModel @Inject constructor() : ViewModel() {


    fun onItemClick(item:TestHolderItem){

    }

    fun onItemLongClick(item:TestHolderItem){

    }
}