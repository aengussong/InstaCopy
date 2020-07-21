package com.aengussong.instacopy.utils

import java.util.concurrent.TimeUnit

class HoursCalculator {

    companion object {
        fun calculateElapsedHours(since: Long) =
            TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - since).toInt()
    }
}