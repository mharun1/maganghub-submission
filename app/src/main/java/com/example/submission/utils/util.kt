package com.example.submission.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun htmlParser(text: String): String {
    return HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
        .toString()
        .trim()
}

@RequiresApi(Build.VERSION_CODES.O)
fun dateFormatter(dateString: String): String {
    val date = LocalDate.parse(dateString)
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("en"))
    return date.format(formatter)
}