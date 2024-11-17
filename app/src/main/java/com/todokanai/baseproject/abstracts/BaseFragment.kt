package com.todokanai.baseproject.abstracts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment(){

    /** onBackPressed를 override 할지 여부 **/
    abstract val backPressedOverride:Boolean

    abstract fun onBackPressedOverride()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(backPressedOverride) {
            requireActivity().onBackPressedDispatcher.addCallback {
                onBackPressedOverride()
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}