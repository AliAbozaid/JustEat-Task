package io.aliabozid.justeat.assets.utils

import android.content.Context


fun Context.dpValueToPx(dpValue: Int): Float = (this.resources.displayMetrics.density * dpValue)