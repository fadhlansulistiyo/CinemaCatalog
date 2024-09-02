package com.fadhlansulistiyo.cinemacatalog.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.moviedetail.DetailMovieScreen
import com.fadhlansulistiyo.cinemacatalog.ui.screen.home.HomeScreen
import com.fadhlansulistiyo.cinemacatalog.ui.navigation.Screen
import com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.peopledetail.DetailPeopleScreen
import com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.tvdetail.DetailTvScreen
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
            if (
                currentRoute != Screen.DetailMovie.route
                && currentRoute != Screen.DetailTv.route
                && currentRoute != Screen.DetailPeople.route
            ) {
                HomeTopAppBar()
            }
        },
        bottomBar = {
            if (
                currentRoute != Screen.DetailMovie.route
                && currentRoute != Screen.DetailTv.route
                && currentRoute != Screen.DetailPeople.route
            ) {
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
                    navigateToMovieDetail = { movieId ->
                        navController.navigate(Screen.DetailMovie.createRoute(movieId))
                    },
                    navigateToTvDetail = { tvId ->
                        navController.navigate(Screen.DetailTv.createRoute(tvId))
                    },
                    navigateToPeopleDetail = { peopleId ->
                        navController.navigate(Screen.DetailPeople.createRoute(peopleId))
                    }
                )
            }
            composable(Screen.Explore.route) {
                Text(text = "Explore Screen")
            }
            composable(Screen.Watchlist.route) {
                WatchlistScreen()
            }
            composable(Screen.Profile.route) {
                Text(text = "Profile Screen")
            }
            composable(
                route = Screen.DetailMovie.route,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) {
                val movieId = it.arguments?.getInt("movieId") ?: -1
                DetailMovieScreen(
                    movieId = movieId,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(
                route = Screen.DetailTv.route,
                arguments = listOf(navArgument("tvId") { type = NavType.IntType })
            ) {
                val tvId = it.arguments?.getInt("tvId") ?: -1
                DetailTvScreen(
                    tvId = tvId,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(
                route = Screen.DetailPeople.route,
                arguments = listOf(navArgument("peopleId") { type = NavType.IntType })
            ) {
                val peopleId = it.arguments?.getInt("peopleId") ?: -1
                DetailPeopleScreen(
                    peopleId = peopleId,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }

        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
private fun CinLogAppPreview() {
    CinemaCatalogTheme {
        CinLogApp()
    }
}