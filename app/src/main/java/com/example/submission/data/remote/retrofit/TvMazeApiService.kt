package com.example.submission.data.remote.retrofit

import com.example.submission.data.remote.response.TvShows
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeApiService {
    @GET("shows")
    suspend fun getShows(
        @Query("page") page: Int
    ): List<TvShows>

    @GET("shows/{id}")
    suspend fun getDetailShow(
        @Path("id") id: Int
    ): TvShows
}