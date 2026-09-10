package com.example.submission.di

import com.example.submission.data.remote.retrofit.TvMazeApiService
import com.example.submission.data.remote.retrofit.retrofit
import com.example.submission.data.repository.TvShowRepository

object Injection {
    private val api: TvMazeApiService = retrofit.create(TvMazeApiService::class.java)

    val tvShowRepository = TvShowRepository(api)
}