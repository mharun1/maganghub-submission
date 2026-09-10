package com.example.submission.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class TvShows(
    val id: Int,
    val image: TvPoster,
    val name: String,
    val rating: TvRating?,
    val summary: String,
    val premiered: String,
    val url: String,
)

@Serializable
data class TvPoster(
    val medium: String,
    val original: String
)

@Serializable
data class TvRating(
    val average: Double?
)