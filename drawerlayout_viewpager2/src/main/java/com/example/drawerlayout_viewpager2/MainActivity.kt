package com.example.drawerlayout_viewpager2

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.drawerlayout_viewpager2.databinding.ActivityMainBinding
import com.example.fragmenttest.FirstFragment
import com.example.fragmenttest.SecondFragment
import com.example.fragmenttest.ThirdFragment
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var binding: ActivityMainBinding
    lateinit var firstFragment: FirstFragment
    lateinit var secondFragment: SecondFragment
    lateinit var thirdFragment: ThirdFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //액션바를 툴바로 대체
        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        /*drawerlayout-fragment*/
        val pageAdapter = PageAdapter(this)
        val title = mutableListOf<String>("phone", "add", "message")
        firstFragment = FirstFragment()
        secondFragment = SecondFragment()
        thirdFragment = ThirdFragment()

        pageAdapter.addFragment(firstFragment, title[0])
        pageAdapter.addFragment(secondFragment, title[1])
        pageAdapter.addFragment(thirdFragment, title[2])

        binding.viewPaser.adapter = pageAdapter
        //탭 레이아웃 뷰페이저를 연걸 위에다가 프레그먼트 먼저 객체
        TabLayoutMediator(binding.tablayout, binding.viewPaser) { tab, position ->
            tab.text = title.get(position)
        }.attach()

        // 익스텐드 플로팅 액션 초기값이 작게
        binding.extendFloatingActionButton.shrink()
        // 익스텐드 플로팅 액션
        binding.extendFloatingActionButton.setOnClickListener{
            when(binding.extendFloatingActionButton.isExtended){
                true -> binding.extendFloatingActionButton.shrink()
                false-> binding.extendFloatingActionButton.extend()
            }
            Toast.makeText(applicationContext, "extend tab click", Toast.LENGTH_SHORT).show()
        }
        //
        binding.navigationview.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_info -> {
                    Toast.makeText(applicationContext, "스케쥴 정보", Toast.LENGTH_SHORT).show()
                }
                R.id.item_report -> {
                    Toast.makeText(applicationContext, "공연 정보", Toast.LENGTH_SHORT).show()
                }
                R.id.item_add -> {
                    Toast.makeText(applicationContext, "티켓 확인", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchText: String?): Boolean {
                Log.d("AppbarProject", "${searchText}")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        when (item.itemId) {
            R.id.menu_save -> {
                Toast.makeText(applicationContext, "save", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_search -> {
                Toast.makeText(applicationContext, "search", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_load -> {
                Toast.makeText(applicationContext, "load", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_open -> {
                Toast.makeText(applicationContext, "open", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}