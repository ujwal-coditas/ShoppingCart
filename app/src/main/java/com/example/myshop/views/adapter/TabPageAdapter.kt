package com.example.myshop.views.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myshop.views.fragments.*

class TabPageAdapter(activity: FragmentActivity, private val tabCount: Int) :
    FragmentStateAdapter(activity) {
    override fun getItemCount() = tabCount
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> CatalogueFragment()
            2 -> FavoriteFragment()
            3 -> ProfileFragment()
            else -> HomeFragment()
        }
    }
}