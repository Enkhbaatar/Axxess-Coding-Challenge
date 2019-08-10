package com.enkhee.codingchallenge.ui.adapter.gridview

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(@NonNull context: Context, @DimenRes itemOffsetId: Int) :
    RecyclerView.ItemDecoration() {
    private val mItemOffset = context.resources.getDimensionPixelOffset(itemOffsetId)
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
    }
}