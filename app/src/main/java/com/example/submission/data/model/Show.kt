package com.example.submission.data.model

data class Show(
    val id: Int,
    val listPoster: String,
    val detailPoster: String,
    val title: String,
    val rating: Double?,
    val summary: String,
    val premiered: String,
    val url: String
)
