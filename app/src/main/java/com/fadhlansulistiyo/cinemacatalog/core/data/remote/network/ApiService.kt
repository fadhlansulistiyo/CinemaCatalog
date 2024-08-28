package com.fadhlansulistiyo.cinemacatalog.core.data.remote.network

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.response.DetailMovieDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.response.ListMovieDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.response.ListPeopleDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.response.ListTvDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.response.MovieCreditsDTO
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

    /*@GET("tv/airing_today?language=en-US&page=1")
    suspend fun getAiringTodayTv(): ListTvDTO

    @GET("tv/{series_id}")
    suspend fun getDetailTv(
        @Path("series_id") seriesId: Int
    ): DetailTvResponse

    @GET("person/{person_id}")
    suspend fun getDetailPeople(
        @Path("person_id") personId: Int
    ): DetailPeopleResponse

    @GET("search/multi")
    suspend fun getMultiSearch(
        @Query("query") query: String,
        @Query("page") page: Int = 0,
        @Query("language") language: String = "en"
    ): ListMultiSearchResponse

    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("page") page: Int = 0,
        @Query("language") language: String = "en"
    ): ListPeopleResponse



    @GET("tv/{series_id}/credits")
    suspend fun getTvCredits(
        @Path("series_id") seriesId: Int
    ): TvCreditsResponse

    @GET("person/{person_id}/combined_credits")
    suspend fun getCredits(
        @Path("person_id") personId: Int
    ): PersonMultiCreditsResponse*/
}