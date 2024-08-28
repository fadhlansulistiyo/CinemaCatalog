package com.fadhlansulistiyo.cinemacatalog.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Watchlist : Screen("watchlist")
    object Profile : Screen("profile")
    object DetailMovie : Screen("home/{movieId}") {
        fun createRoute(movieId: Int) = "home/$movieId"
    }
}