package com.example.drawerlayout_viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.drawerlayout_viewpager2.databinding.ActivityMainBinding
import com.example.fragmenttest.FirstFragment
import com.example.fragmenttest.SecondFragment
import com.example.fragmenttest.ThirdFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //액션바대신에 툴바로 대체
        setSupportActionBar(binding.toolbar)
        //ActionBarDrawerToggle버튼
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        )
        //업버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //토글 싱크
        toggle.syncState()

        //어댑터 생성
        val pageAdapter = PageAdapter(this)
        val titlelist = mutableListOf<String>("timetable", "info", "tickeck")

        pageAdapter.addFragment(FirstFragment(), titlelist[0])
        pageAdapter.addFragment(SecondFragment(), titlelist[1])
        pageAdapter.addFragment(ThirdFragment(), titlelist[2])

        binding.viewpager.adapter = pageAdapter
        //탭레이아웃 뷰페이저 연동
        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
            tab.text = titlelist[position]


        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 토글버튼에서 발생하면
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}