package com.todokanai.baseproject.components.activity

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.todokanai.baseproject.abstracts.BaseActivity
import com.todokanai.baseproject.databinding.ActivityMainBinding
import com.todokanai.baseproject.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    private val viewModel : MainViewModel by viewModels()

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
        onBackPressedDispatcher.addCallback {

        }
        setContentView(binding.root)
    }
}