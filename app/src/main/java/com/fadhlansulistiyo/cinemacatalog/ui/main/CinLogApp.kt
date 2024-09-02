package com.fadhlansulistiyo.cinemacatalog.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.INVALID_ID
import com.fadhlansulistiyo.cinemacatalog.ui.navigation.Screen
import com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.moviedetail.DetailMovieScreen
import com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.peopledetail.DetailPeopleScreen
import com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.tvdetail.DetailTvScreen
import com.fadhlansulistiyo.cinemacatalog.ui.screen.explore.ExploreScreen
import com.fadhlansulistiyo.cinemacatalog.ui.screen.home.HomeScreen
import com.fadhlansulistiyo.cinemacatalog.ui.screen.profile.ProfileScreen
import com.fadhlansulistiyo.cinemacatalog.ui.screen.watchlist.WatchlistScreen
import com.fadhlansulistiyo.cinemacatalog.ui.theme.CinemaCatalogTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinLogApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (shouldShowTopBar(currentRoute)) {
                HomeTopAppBar()
            }
        },
        bottomBar = {
            if (shouldShowBottomBar(currentRoute)) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToMovieDetail = navigateToMovieDetail(navController),
                    navigateToTvDetail = navigateToTvDetail(navController),
                    navigateToPeopleDetail = navigateToPeopleDetail(navController)
                )
            }
            composable(Screen.Explore.route) {
                ExploreScreen(
                    navigateToMovieDetail = navigateToMovieDetail(navController),
                    navigateToTvDetail = navigateToTvDetail(navController)
                )
            }
            composable(Screen.Watchlist.route) {
                WatchlistScreen(
                    navigateToMovieDetail = navigateToMovieDetail(navController),
                    navigateToTvDetail = navigateToTvDetail(navController)
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailMovie.route,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) {
                val movieId = it.arguments?.getInt("movieId") ?: INVALID_ID
                DetailMovieScreen(
                    movieId = movieId,
                    navigateBack = navController::navigateUp
                )
            }
            composable(
                route = Screen.DetailTv.route,
                arguments = listOf(navArgument("tvId") { type = NavType.IntType })
            ) {
                val tvId = it.arguments?.getInt("tvId") ?: INVALID_ID
                DetailTvScreen(
                    tvId = tvId,
                    navigateBack = navController::navigateUp
                )
            }
            composable(
                route = Screen.DetailPeople.route,
                arguments = listOf(navArgument("peopleId") { type = NavType.IntType })
            ) {
                val peopleId = it.arguments?.getInt("peopleId") ?: INVALID_ID
                DetailPeopleScreen(
                    peopleId = peopleId,
                    navigateBack = navController::navigateUp
                )
            }
        }
    }
}

@Composable
fun shouldShowTopBar(currentRoute: String?): Boolean {
    return when (currentRoute) {
        Screen.DetailMovie.route,
        Screen.DetailTv.route,
        Screen.DetailPeople.route,
        Screen.Explore.route,
        Screen.Watchlist.route,
        Screen.Profile.route -> false
        else -> true
    }
}

@Composable
fun shouldShowBottomBar(currentRoute: String?): Boolean {
    return when (currentRoute) {
        Screen.DetailMovie.route,
        Screen.DetailTv.route,
        Screen.DetailPeople.route -> false
        else -> true
    }
}

fun navigateToMovieDetail(navController: NavHostController): (Int) -> Unit = { movieId ->
    navController.navigate(Screen.DetailMovie.createRoute(movieId))
}

fun navigateToTvDetail(navController: NavHostController): (Int) -> Unit = { tvId ->
    navController.navigate(Screen.DetailTv.createRoute(tvId))
}

fun navigateToPeopleDetail(navController: NavHostController): (Int) -> Unit = { peopleId ->
    navController.navigate(Screen.DetailPeople.createRoute(peopleId))
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
private fun CinLogAppPreview() {
    CinemaCatalogTheme {
        CinLogApp()
    }
}