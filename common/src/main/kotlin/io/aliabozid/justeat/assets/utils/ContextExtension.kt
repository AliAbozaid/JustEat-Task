package io.aliabozid.justeat.assets.utils

import android.content.Context
import androidx.annotation.DimenRes

fun Context.dpValueToPx(dpValue: Int): Float = (this.resources.displayMetrics.density * dpValue)

fun Context.getDimension(@DimenRes id: Int): Int {
    return resources.getDimensionPixelSize(id)
}
