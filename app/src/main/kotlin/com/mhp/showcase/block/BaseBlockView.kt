package com.mhp.showcase.block

import android.content.Context
import com.mhp.showcase.R
import java.lang.String.format
import java.util.*

/**
 * Actual view to be rendered in a block layout of a [android.app.Fragment] or [android.app.Activity]
 */
interface BaseBlockView<T : BaseBlock> {

    var block: T?

    fun afterViews()

    fun convertTimeToText(time: Long?, context: Context): String? {
        if (time == null)
            return null

        if (getYearsBetween(time) > 1) {
            return format(context.getString(R.string.block_view_years_ago),
                    getYearsBetween(time).toString())
        }
        if (getMonthsBetween(time) > 1) {
            return format(context.getString(R.string.block_view_months_ago),
                    getMonthsBetween(time).toString())
        }
        if (getDaysBetween(time) > 1) {
            return format(context.getString(R.string.block_view_days_ago),
                    getDaysBetween(time).toString())
        }
        if (getHoursBetween(time) > 1) {
            return format(context.getString(R.string.block_view_hours_ago),
                    getHoursBetween(time).toString())
        }
        if (getMinutesBetween(time) > 1) {
            return format(context.getString(R.string.block_view_minutes_ago),
                    getMinutesBetween(time).toString())
        }
        return format(context.getString(R.string.block_view_seconds_ago),
                getSecondsBetween(time).toString())
    }

    private fun getYearsBetween(time: Long): Int {
        return ((Date().time - time) / (31556952000)).toInt()
    }

    private fun getMonthsBetween(time: Long): Int {
        return ((Date().time - time) / (2629746000)).toInt()
    }

    private fun getDaysBetween(time: Long): Int {
        return ((Date().time - time) / (24 * 60 * 60 * 1000)).toInt()
    }

    private fun getHoursBetween(time: Long): Int {
        return ((Date().time - time) / (60 * 60 * 1000)).toInt()
    }

    private fun getMinutesBetween(time: Long): Int {
        return ((Date().time - time) / (60 * 1000)).toInt()
    }

    private fun getSecondsBetween(time: Long): Int {
        return ((Date().time - time) / (1000)).toInt()
    }
}
