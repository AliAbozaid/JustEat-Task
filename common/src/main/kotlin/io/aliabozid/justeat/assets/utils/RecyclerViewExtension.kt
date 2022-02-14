package io.aliabozid.justeat.assets.utils

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.aliabozid.justeat.assets.R

fun RecyclerView.addDividerDecorator(
    @DimenRes startMarginId: Int = R.dimen.space_0,
    @DimenRes endMarginId: Int = R.dimen.space_0,
    drawableResId: Int = R.drawable.recycler_separator,
    @ColorRes tintColor: Int = 0
) {
    val layoutManger = layoutManager as LinearLayoutManager
    if (layoutManger.orientation == LinearLayoutManager.HORIZONTAL) {
        addVerticalDividerDecorator(
            startMarginId,
            endMarginId,
            drawableResId,
            tintColor
        )
    } else {
        addHorizontalDividerDecorator(
            startMarginId,
            endMarginId,
            drawableResId,
            tintColor
        )
    }
}

fun RecyclerView.addHorizontalDividerDecorator(
    @DimenRes startMarginId: Int = R.dimen.space_0,
    @DimenRes endMarginId: Int = R.dimen.space_0,
    drawableResId: Int = R.drawable.recycler_separator,
    @ColorRes tintColor: Int = 0
) {
    addItemDecoration(
        CustomDividerItemDecoration(
            context,
            marginLeftRes = startMarginId,
            marginRightRes = endMarginId,
            drawableResId = drawableResId,
            tintColor = tintColor
        )
    )
}

fun RecyclerView.addVerticalDividerDecorator(
    @DimenRes marginTopRes: Int = R.dimen.space_0,
    @DimenRes marginBottomRes: Int = R.dimen.space_0,
    drawableResId: Int = 0,
    @ColorRes tintColor: Int = 0
) {
    addItemDecoration(
        CustomDividerItemDecoration(
            context,
            marginBottomRes = marginBottomRes,
            marginTopRes = marginTopRes,
            drawableResId = drawableResId,
            tintColor = tintColor,
            orientation = CustomDividerItemDecoration.VERTICAL
        )
    )
}
