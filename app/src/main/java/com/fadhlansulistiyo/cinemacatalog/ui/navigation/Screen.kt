package com.fadhlansulistiyo.cinemacatalog.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Watchlist : Screen("watchlist")
    object Profile : Screen("profile")
    object DetailMovie : Screen("home/movie/{movieId}") {
        fun createRoute(movieId: Int) = "home/movie/$movieId"
    }
    object DetailTv : Screen("home/tv/{tvId}") {
        fun createRoute(tvId: Int) = "home/tv/$tvId"
    }
    object DetailPeople : Screen("home/people/{peopleId}") {
        fun createRoute(peopleId: Int) = "home/people/$peopleId"
    }
}