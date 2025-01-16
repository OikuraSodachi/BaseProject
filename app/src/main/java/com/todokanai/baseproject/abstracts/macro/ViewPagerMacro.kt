package com.todokanai.baseproject.abstracts.macro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.todokanai.baseproject.adapters.FragmentAdapter

/**
 * @param viewPager [ViewPager2] instance
 * @param fragList list of fragments
 * @param userInputEnabled enable swipe
 *
 * **/
abstract class ViewPagerMacro(
    private val viewPager:ViewPager2,
    private val fragList:List<Fragment>,
    private val userInputEnabled:Boolean = false
) {

    fun applyMacro(activity: FragmentActivity,tabLayout: TabLayout){
        val adapter = FragmentAdapter(activity).apply {
            fragmentList = fragList
        }
        viewPager.run{
            this.adapter = adapter
            this.isUserInputEnabled = userInputEnabled
        }
        TabLayoutMediator(tabLayout,viewPager){ tab,position ->
            onSetTabLayout(tab,position)
        }.attach()
    }

    /**
     * @param tab [TabLayout.Tab] item
     * @param position position of item
     *
     * **/
    abstract fun onSetTabLayout(tab:TabLayout.Tab, position:Int)

}