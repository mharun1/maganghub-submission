package com.example.submission.data.mapper

import com.example.submission.data.model.Show
import com.example.submission.data.remote.response.TvShows

fun TvShows.toDomain() : Show {
    return Show(
        id = id,
        listPoster = image.medium,
        detailPoster = image.original,
        title = title,
        rating = rating?.average,
        premiered = premiered
    )
}

fun List<TvShows>.toDomain(): List<Show> = map { it.toDomain() }