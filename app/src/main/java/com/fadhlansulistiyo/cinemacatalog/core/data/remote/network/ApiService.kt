package com.fadhlansulistiyo.cinemacatalog.core.data.remote.network

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.DetailMovieDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.DetailPeopleDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.DetailTvDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.ListMovieDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.ListMultiSearchDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.ListPeopleDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.ListTvDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.MovieCreditsDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.PersonMultiCreditsDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.TvCreditsDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getNowPlaying(
        @Query("page") page: Int = 0,
    ): ListMovieDTO

    @GET("movie/{movie_id}/credits?language=en-US")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int
    ): MovieCreditsDTO

    @GET("tv/top_rated?language=en-US")
    suspend fun getTopRatedTv(): ListTvDTO

    @GET("trending/person/week")
    suspend fun getTrendingPeople(): ListPeopleDTO

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int
    ): DetailMovieDTO

    @GET("tv/{series_id}")
    suspend fun getDetailTv(
        @Path("series_id") seriesId: Int
    ): DetailTvDTO

    @GET("tv/{series_id}/credits")
    suspend fun getTvCredits(
        @Path("series_id") seriesId: Int
    ): TvCreditsDTO

    @GET("person/{person_id}")
    suspend fun getDetailPeople(
        @Path("person_id") personId: Int
    ): DetailPeopleDTO

    @GET("person/{person_id}/combined_credits")
    suspend fun getCredits(
        @Path("person_id") personId: Int
    ): PersonMultiCreditsDTO

    @GET("search/multi")
    suspend fun getMultiSearch(
        @Query("query") query: String,
        @Query("page") page: Int = 0,
        @Query("language") language: String = "en"
    ): ListMultiSearchDTO
}