package com.example.submission.di

import android.content.Context
import com.example.submission.data.remote.retrofit.TvMazeApiService
import com.example.submission.data.remote.retrofit.retrofit
import com.example.submission.data.repository.TvShowRepository

object Injection {
    private val api: TvMazeApiService = retrofit.create(TvMazeApiService::class.java)

    val tvShowRepository = TvShowRepository(api)
}