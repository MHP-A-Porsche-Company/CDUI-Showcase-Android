package com.mhp.showcase.block

import java.util.*

/**
 * Actual view to be rendered in a block layout of a [android.app.Fragment] or [android.app.Activity]
 */
interface BaseBlockView<T : BaseBlock> {

    var block: T?

    fun afterViews()

    fun convertTimeToText(time: Long?): String? {
        if (time == null)
            return null

        if (getYearsBetween(time) > 0) {
            return getYearsBetween(time).toString() + " years ago"
        }
        if (getMonthsBetween(time) > 0) {
            return getMonthsBetween(time).toString() + " months ago "
        }
        if (getDaysBetween(time) > 0) {
            return getDaysBetween(time).toString() + " days ago"
        }
        if (getHoursBetween(time) > 0) {
            return getHoursBetween(time).toString() + " hours ago"
        }
        if (getMinutesBetween(time) > 0) {
            return "Vor " + getMinutesBetween(time) + " minutes ago"
        }
        return "Vor " + getSecondsBetween(time) + " seconds ago"
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
