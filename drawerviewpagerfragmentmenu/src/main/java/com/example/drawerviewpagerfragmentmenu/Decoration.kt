package com.example.drawerviewpagerfragmentmenu

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

class Decoration(val context: Context) : RecyclerView.ItemDecoration() {
    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val width = parent.width
        val height = parent.height
        val kboDrawable = ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)
        val kboWidth = kboDrawable?.intrinsicWidth
        val kboHeight = kboDrawable?.intrinsicHeight
        val left = width / 2 - kboWidth?.div(2) as Int
        val top = height / 2 - kboHeight?.div(2) as Int

        canvas.drawBitmap(
            BitmapFactory.decodeResource(context.resources, R.drawable.kbo),
            left.toFloat(), top.toFloat(), null
        )
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent:RecyclerView, state:RecyclerView.State) {
        super.getItemOffsets(outRect,view, parent, state)
        val index = parent.getChildAdapterPosition(view)+1
        if(index%3==0){
            outRect.set(10,10,10,60)
        }else{
            outRect.set(10,10,10,0)
        }
        view.setBackgroundColor(Color.parseColor("#FFE1BF5A"))
        ViewCompat.setElevation(view, 20.0f)
    }
}