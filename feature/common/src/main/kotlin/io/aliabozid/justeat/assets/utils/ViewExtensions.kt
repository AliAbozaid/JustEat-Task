package io.aliabozid.justeat.assets.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.core.view.updatePadding

val ViewGroup.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)
