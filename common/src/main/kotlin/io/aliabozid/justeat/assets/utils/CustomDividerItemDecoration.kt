package io.aliabozid.justeat.assets.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.aliabozid.justeat.assets.R

class CustomDividerItemDecoration constructor(
    context: Context,
    @DimenRes marginLeftRes: Int = R.dimen.space_0,
    @DimenRes marginRightRes: Int = R.dimen.space_0,
    @DimenRes marginTopRes: Int = R.dimen.space_0,
    @DimenRes marginBottomRes: Int = R.dimen.space_0,
    drawableResId: Int = 0,
    @ColorRes tintColor: Int = 0,
    private val orientation: Int = HORIZONTAL
) : RecyclerView.ItemDecoration() {

    companion object {
        const val HORIZONTAL = 1
        const val VERTICAL = 2

        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }

    private var divider: Drawable?
    private var marginLeft: Int
    private var marginRight: Int
    private var marginTop: Int
    private var marginBottom: Int

    init {
        if (drawableResId != 0) {
            divider = ContextCompat.getDrawable(context, drawableResId)
        } else {
            val styledAttributes = context.obtainStyledAttributes(ATTRS)
            divider = styledAttributes.getDrawable(0)
            styledAttributes.recycle()
        }

        if (tintColor != 0) {
            divider?.setTint(ContextCompat.getColor(context, tintColor))
        }

        marginLeft = context.getDimension(marginLeftRes)
        marginRight = context.getDimension(marginRightRes)
        marginTop = context.getDimension(marginTopRes)
        marginBottom = context.getDimension(marginBottomRes)
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (divider == null) {
            super.onDraw(canvas, parent, state)
            return
        }

        when (orientation) {
            HORIZONTAL -> drawHorizontal(canvas, parent)
            VERTICAL -> drawVertical(canvas, parent)
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop + marginTop
        val bottom = parent.height - parent.paddingBottom - marginBottom

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val left = child.left + params.rightMargin
            val right = left + (divider?.intrinsicWidth ?: 0)

            divider?.setBounds(left, top, right, bottom)
            divider?.draw(canvas)
        }
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft + marginLeft
        val right = parent.width - parent.paddingRight - marginRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + (divider?.intrinsicHeight ?: 0)

            divider?.setBounds(left, top, right, bottom)
            divider?.draw(canvas)
        }
    }
}
