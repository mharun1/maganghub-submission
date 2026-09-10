package com.example.submission.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.submission.data.mapper.toDomain
import com.example.submission.data.model.Show
import com.example.submission.data.remote.retrofit.TvMazeApiService

interface Repository {
    suspend fun getShows(): List<Show>
    suspend fun getDetailShow(id: Int): Show
}

class TvShowRepository(
    private val apiService: TvMazeApiService
): Repository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getShows(): List<Show> {
        return apiService.getShows(page = 0).toDomain()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getDetailShow(id: Int): Show {
        return apiService.getDetailShow(id).toDomain()
    }
}