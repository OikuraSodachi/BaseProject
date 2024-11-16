package com.todokanai.baseproject.viewmodel

import androidx.lifecycle.ViewModel
import com.todokanai.baseproject.data.dataclass.TestHolderItem
import com.todokanai.baseproject.viewmodel.submodel.TestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestFragViewModel @Inject constructor() : ViewModel() {

    lateinit var test : TestModel

    fun initVariables(){
        test = TestModel()
    }


    fun onItemClick(item:TestHolderItem){

    }

    fun onItemLongClick(item:TestHolderItem){

    }
}