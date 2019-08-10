package com.enkhee.codingchallenge.utils

import android.content.Context
import android.util.DisplayMetrics

fun calculateNoOfColumns(context: Context, columnWithDp: Int) : Int{
    val displayMetrics:DisplayMetrics = context.resources.displayMetrics
    val screenWithDp:Float = displayMetrics.widthPixels / displayMetrics.density
    return (screenWithDp/columnWithDp + 0.5).toInt()
}