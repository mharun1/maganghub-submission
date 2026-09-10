package com.example.submission.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.submission.data.model.Show
import com.example.submission.data.remote.response.TvShows
import com.example.submission.utils.dateFormatter
import com.example.submission.utils.htmlParser

@RequiresApi(Build.VERSION_CODES.O)
fun TvShows.toDomain() : Show {
    return Show(
        id = id,
        listPoster = image.medium,
        detailPoster = image.original,
        title = name,
        rating = rating?.average,
        summary = htmlParser(summary),
        premiered = dateFormatter(premiered),
        url = url
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun List<TvShows>.toDomain(): List<Show> = map { it.toDomain() }