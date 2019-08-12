package com.enkhee.codingchallenge.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.R.attr.columnWidth
import com.enkhee.codingchallenge.ui.adapter.gridview.SpacesItemDecoration
import kotlin.math.max

class AutofitRecyclerView : RecyclerView {
    private lateinit var manager: GridLayoutManager
    private var colWith: Int = -1

    constructor(context: Context) : super(context) {
        initialize(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, def: Int) : super(context, attrs, def) {
        initialize(context, attrs)
    }

    @SuppressLint("Recycle")
    private fun initialize(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val attrsArray = intArrayOf(columnWidth)
            val array: TypedArray = context.obtainStyledAttributes(attrs, attrsArray)
            colWith = array.getDimensionPixelOffset(0, -1)
            array.recycle()
        }

        manager = GridLayoutManager(context, 1)
        layoutManager = manager
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)

        if (colWith > 0) {
            val spanCount = max(1, measuredWidth / colWith)
            manager.spanCount = spanCount
        }
    }
}