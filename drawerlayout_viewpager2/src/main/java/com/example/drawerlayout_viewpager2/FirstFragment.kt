package com.example.fragmenttest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.drawerlayout_viewpager2.MainActivity
import com.example.drawerlayout_viewpager2.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.fr01Btn01.setOnClickListener{
            Toast.makeText(context,"fragment first image", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}