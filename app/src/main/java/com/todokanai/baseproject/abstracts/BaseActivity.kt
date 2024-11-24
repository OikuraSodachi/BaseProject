package com.todokanai.baseproject.abstracts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.todokanai.baseproject.myobjects.Constants.PERMISSION_REQUEST_CODE
import com.todokanai.baseproject.myobjects.Objects
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
/**
 * handles permissions
 * **/
abstract class BaseActivity: AppCompatActivity() {

    private val permissions:Array<String> = Objects.permissions
    private val requestCode:Int = PERMISSION_REQUEST_CODE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(permissions.isNotEmpty()){
            requestPermissions(permissions,requestCode)
        }
    }
}