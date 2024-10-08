package com.fadhlansulistiyo.cinemacatalog.core.utils

import com.fadhlansulistiyo.cinemacatalog.BuildConfig

object Constants {
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    const val IMAGE_URL_BACKDROP = "https://image.tmdb.org/t/p/w780/"
    const val IMAGE_URL_ORIGINAL = "https://image.tmdb.org/t/p/original"
    const val DATA_IS_EMPTY = "Data is empty."
    const val DATA_IS_NULL = "Data is null."
    const val NO_INTERNET_CONNECTION = "No internet connection."
    const val NETWORK_ERROR = "Network error."
    const val UNKNOWN_ERROR = "Unknown error."
    const val NA = "N/A"

    const val NOW_PLAYING = "Now Playing Movies"
    const val TOP_RATED_TV = "Top Rated TV"
    const val TRENDING_PEOPLE = "Trending People"

    const val GENRES = "Genres"
    const val OVERVIEW = "Overview"
    const val BIOGRAPHY = "Biography"
    const val PRODUCTION_COMPANIES = "Production Companies"
    const val CAST = "Cast"

    const val KNOWN_FOR = "Known For"
    const val BIRTHDAY = "Birthday"
    const val PLACE_OF_BIRTH = "Place of Birth"
    const val INVALID_ID = -1
}