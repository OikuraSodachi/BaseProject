package com.todokanai.baseproject.components.activity

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.todokanai.baseproject.R
import com.todokanai.baseproject.abstracts.BaseActivity
import com.todokanai.baseproject.adapters.FragmentAdapter
import com.todokanai.baseproject.components.fragments.MultiSelectFragment
import com.todokanai.baseproject.components.fragments.TestFragment
import com.todokanai.baseproject.databinding.ActivityMainBinding
import com.todokanai.baseproject.myobjects.Constants.PERMISSION_REQUEST_CODE
import com.todokanai.baseproject.myobjects.Objects
import com.todokanai.baseproject.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override val permissions = Objects.permissions
    override val requestCode = PERMISSION_REQUEST_CODE

    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    //    val composeViewVisibility = mutableStateOf(false)

        val fragList = listOf(MultiSelectFragment(),TestFragment())
        val viewPagerAdapter = FragmentAdapter(this)
        viewPagerAdapter.fragmentList = fragList

        binding.run{
            mainViewPager.run{
                //isUserInputEnabled = false
                adapter = viewPagerAdapter
            }

            TabLayoutMediator(binding.bottomTab,binding.mainViewPager){tab,position ->
                val list = listOf(getString(R.string.frag1),getString(R.string.frag2))
                tab.text =  list[position]
            }.attach()
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
    }
}