package com.example.submission

import com.example.submission.data.model.Show

object Dummy {
    fun generateDummyDetailShow(id: Int) : Show {
        val item = Show(
            id = id,
            listPoster = "https://example.com/medium-poster$id.jpg",
            detailPoster = "https://example.com/original-poster$id.jpg",
            title = "Show $id",
            rating = if (id % 2 == 0) null else 6.6,
            summary = "Summary $id",
            premiered = "2026-09-0$id",
            url = "https://example.com/shows/$id/show-$id"
        )
        return item
    }
    fun generateDummyShows(): List<Show> {
        return (0..5).map { generateDummyDetailShow(it) }
    }
}