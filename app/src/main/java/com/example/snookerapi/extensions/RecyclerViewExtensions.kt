package com.example.snookerapi.extensions

import android.graphics.Rect
import android.view.View
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addHorizontalSpace(@DrawableRes spaceRes: Int) {
    addItemDecoration(
        object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State,
            ) {
                val position = parent.getChildAdapterPosition(view)
                if (position != 0 && position != parent.adapter?.itemCount) {
                    outRect.top = spaceRes
                }
            }
        }
    )
}