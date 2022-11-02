package com.example.drawerviewpagerfragmentmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.drawerviewpagerfragmentmenu.databinding.ActivityMainBinding
import com.example.drawerviewpagerfragmentmenu.databinding.UsertabButtonBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var  binding: ActivityMainBinding
    lateinit var  firstFragment: FirstFragment
    lateinit var  secondFragment: SecondFragment
    lateinit var  thirdFragment: ThirdFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //액션바 대신 툴바 대체
        setSupportActionBar(binding.toolbar)
        //액션바토글버튼 적용
        toggle = ActionBarDrawerToggle(this,binding.drawerLayout,R.string.drawer_opend, R.string.drawer_closed)
        //업버튼활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //토글 동기화
        toggle.syncState()
        //여기까지하고 임폴트 오버라이드

        /*drawerlayout-fragment*/
        val pagerAdabter = PagerAdabter(this)
        val title = mutableListOf<String>("phone", "add", "message")
        firstFragment = FirstFragment()
        secondFragment = SecondFragment()
        thirdFragment = ThirdFragment()

        pagerAdabter.addFragment(firstFragment,title[0])
        pagerAdabter.addFragment(secondFragment,title[1])
        pagerAdabter.addFragment(thirdFragment,title[2])

        binding.viewPager.adapter = pagerAdabter

        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab,position ->
            tab.customView = creatTabView(title[position])
        }.attach()

    }

    private fun creatTabView(title: String): View {
        val useTabBinding = UsertabButtonBinding.inflate(layoutInflater)
        useTabBinding.usertabTv.text = title
        when(title){
            "phone" ->{useTabBinding.usertabIcMessage.setImageResource(R.drawable.ic_phone)}
            "add"->{useTabBinding.usertabIcMessage.setImageResource(R.drawable.ic_add)}
            "message"->{useTabBinding.usertabIcMessage.setImageResource(R.drawable.ic_message)}
        }
        return  useTabBinding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //토글버튼에서 이벤트가 발생하면 처리
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}