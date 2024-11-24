package com.todokanai.baseproject.abstracts

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity: AppCompatActivity() {

    /** Todo: 이거 viewModel로 가야할듯? **/
    abstract val permissions:Array<String>
    abstract val requestCode:Int

    /** onBackPressed를 override 할지 여부 **/
    abstract val backPressedOverride:Boolean

    abstract fun onBackPressedOverride()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(permissions.isNotEmpty()){
            requestPermissions(permissions,requestCode)
        }
        if(backPressedOverride) {
            onBackPressedDispatcher.addCallback {
                onBackPressedOverride()
            }
        }
    }
}