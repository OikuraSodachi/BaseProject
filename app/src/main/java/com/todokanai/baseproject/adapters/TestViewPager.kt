package com.todokanai.baseproject.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.todokanai.baseproject.abstracts.macro.ViewPagerMacro

class TestViewPager(
    viewPager:ViewPager2,
    fragList:List<Fragment>,
    val textList:List<String>
): ViewPagerMacro(
    viewPager,
    fragList
) {
    override fun onSetTabLayout(tab: TabLayout.Tab, position: Int) {
        tab.text = textList[position]
    }
}