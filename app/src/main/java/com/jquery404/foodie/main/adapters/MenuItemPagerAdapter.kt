package com.jquery404.foodie.main.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MenuItemPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragmentList: MutableList<Fragment> = mutableListOf()
    private val fragmentListTitle: MutableList<String> = mutableListOf()


    fun addFragment(fragment: Fragment, title: String, position: Int) {
        fragmentList.add(position, fragment)
        fragmentListTitle.add(position, title)
    }

    fun removeFragment(position: Int) {
        fragmentList.removeAt(position)
        fragmentListTitle.removeAt(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentListTitle[position]
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }


}