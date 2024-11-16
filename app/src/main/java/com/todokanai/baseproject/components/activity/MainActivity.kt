package com.todokanai.baseproject.components.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.todokanai.baseproject.abstracts.BaseActivity
import com.todokanai.baseproject.databinding.ActivityMainBinding
import com.todokanai.baseproject.myobjects.Objects
import com.todokanai.baseproject.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    private val viewModel : MainViewModel by viewModels()
    override val permissions: Array<String>
        get() = Objects.permissions
    override val requestCode: Int
        get() = 111
    override val backPressedOverride: Boolean
        get() = true

    override fun onBackPressedOverride() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val composeViewVisibility = mutableStateOf(false)

        binding.run{
            composeTest.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    MaterialTheme {
                        if (composeViewVisibility.value) {
                            /** Todo **/

                        }
                    }
                }
            }

        }

        setContentView(binding.root)
    }
}