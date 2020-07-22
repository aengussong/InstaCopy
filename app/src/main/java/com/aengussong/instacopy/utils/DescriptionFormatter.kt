package com.aengussong.instacopy.utils

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import java.util.regex.Pattern

class DescriptionFormatter {

    companion object {
        private val TAG_PATTERN = Pattern.compile("[@#]\\w+")

        fun format(username: String, description: String): Spannable {
            return SpannableStringBuilder(username).apply {
                setSpan(
                    StyleSpan(BOLD),
                    0,
                    length,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
                append(" $description")

                val matcher = TAG_PATTERN.matcher(this)
                while (matcher.find()) {
                    setSpan(
                        ForegroundColorSpan(Color.BLUE),
                        matcher.start(),
                        matcher.end(),
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                    )
                }
            }
        }
    }
}