package com.example.submission.utils

import androidx.core.text.HtmlCompat
import java.util.Date

fun HtmlParser(text: String): String {
    return HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
        .toString()
        .trim()
}

fun DateConverter(date: String) {

}