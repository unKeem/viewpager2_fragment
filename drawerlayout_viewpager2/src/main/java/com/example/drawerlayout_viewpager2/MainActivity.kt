package com.example.drawerlayout_viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import com.example.drawerlayout_viewpager2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //어댑터 생성
        val pageAdapter = PagerAdapter(this)
        val titlelist = mutableListOf<String>("search","call","photo")

        pageAdapter.addFragment(FirstFragment(), titlelist[0])
        pageAdapter.addFragment(SecondFragment(), titlelist[1])
        pageAdapter.addFragment(ThirdFragment(), titlelist[2])

        binding.viewpager.adapter = pageAdapter
        //탭레이아웃 뷰페이저 연동
        TabLayoutMediator(binding.tabLayout, binding.viewpager){tab,position ->
//            tab.text = titlelist[position]  글자만 넣을때 tabCreatview 필요없이
            tab.setCustomView(tabCreateView(titlelist[position]))


        }.attach()
    }

    private fun tabCreateView(title: String): View {
        val usertabButtonBinding = UsertabButtonBinding.inflate(layoutInflater)
        usertabButtonBinding.tvTab.text = title
        when(title){
            "search"->usertabButtonBinding.ivTab.setImageResource(R.drawable.ic_search)
            "call"->usertabButtonBinding.ivTab.setImageResource(R.drawable.ic_phone)
            "photo"->usertabButtonBinding.ivTab.setImageResource(R.drawable.ic_camera)
        }
        return usertabButtonBinding.root
    }
    }
}