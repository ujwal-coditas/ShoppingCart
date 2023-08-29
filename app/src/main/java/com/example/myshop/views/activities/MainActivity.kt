package com.example.myshop.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myshop.databinding.ActivityMainBinding
import com.example.myshop.views.adapter.TabPageAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpTabBar()


    }

        private fun setUpTabBar() {
            val tabPageAdapter = TabPageAdapter(this, binding.tlTabBar.tabCount)
            binding.vpTabLayout.adapter = tabPageAdapter
            binding.vpTabLayout.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.tlTabBar.selectTab(binding.tlTabBar.getTabAt(position))
                }
            })

            binding.tlTabBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    binding.vpTabLayout.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
}
