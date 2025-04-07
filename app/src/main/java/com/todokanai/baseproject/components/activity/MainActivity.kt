package com.todokanai.baseproject.components.activity

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.todokanai.baseproject.R
import com.todokanai.baseproject.adapters.TestViewPager
import com.todokanai.baseproject.components.fragments.MultiSelectFragment
import com.todokanai.baseproject.components.fragments.TestFragment
import com.todokanai.baseproject.databinding.ActivityMainBinding
import com.todokanai.baseproject.myobjects.Constants.PERMISSION_REQUEST_CODE
import com.todokanai.baseproject.myobjects.Objects
import com.todokanai.baseproject.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val permissions = Objects.permissions
    val requestCode = PERMISSION_REQUEST_CODE

    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(permissions.isNotEmpty()){
            requestPermissions(permissions,requestCode)
        }
    //    val composeViewVisibility = mutableStateOf(false)
        val fragList = listOf(MultiSelectFragment(),TestFragment())
        val textList = listOf(getString(R.string.frag1),getString(R.string.frag2))

        val temp = TestViewPager(
            viewPager = binding.mainViewPager,
            fragList = fragList,
            textList = textList
        )

        temp.applyMacro(
            this,
            binding.bottomTab
        )


        binding.run{
            /*
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
             */
        }

        onBackPressedDispatcher.addCallback {

        }
        setContentView(binding.root)

    }
}