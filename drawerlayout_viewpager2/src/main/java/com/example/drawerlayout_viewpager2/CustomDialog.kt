package com.example.drawerlayout_viewpager2

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.drawerlayout_viewpager2.databinding.DialogCustomBinding
import com.example.drawerlayout_viewpager2.databinding.FragmentFirstBinding
import com.example.fragmenttest.FirstFragment


class CustomDialog(val context: Context, val firstFragment: FirstFragment) {
    val dialog = Dialog(context)

    fun showDialog() {
        val binding = DialogCustomBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(true)

        dialog.setCancelable(true)
        dialog.show()

        binding.dialogBtnCancle.setOnClickListener {
            dialog.dismiss()
        }
        binding.dialogBtnOk.setOnClickListener {
            val time = (Math.random()*100).toInt()
            val date = binding.dialogEtvDate.text.toString()
            val hour = binding.dialogEtvHour.text.toString()
            var dataVO: DataVO

            dataVO = if(time%4 ==0){
                DataVO(time, date, hour, R.drawable.item_img00)
            }else if(time%4 ==1){
                DataVO(time, date, hour, R.drawable.item_img01)
            }else if(time%4 ==2){
                DataVO(time, date, hour, R.drawable.item_img02)
            }else {
                DataVO(time, date, hour, R.drawable.item_img03)
            }
            (context as MainActivity).firstFragment.refreshRecyclerViewAdd(dataVO)
            dialog.dismiss()
        }
    }
}