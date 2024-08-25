package com.fadhlansulistiyo.cinemacatalog.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fadhlansulistiyo.cinemacatalog.R
import com.fadhlansulistiyo.cinemacatalog.ui.navigation.NavigationItem
import com.fadhlansulistiyo.cinemacatalog.ui.navigation.Screen
import com.fadhlansulistiyo.cinemacatalog.ui.theme.*

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.menu_explore),
                icon = Icons.Default.Search,
                screen = Screen.Explore
            ),
            NavigationItem(
                title = stringResource(R.string.menu_watchlist),
                icon = Icons.Default.Favorite,
                screen = Screen.Watchlist
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        item.title,
                    )
                },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = md_theme_primary_light,
                    unselectedIconColor = md_theme_secondaryContainer_light,
                    selectedTextColor = md_theme_primary_light,
                    unselectedTextColor = md_theme_secondaryContainer_light,
                    indicatorColor = md_theme_secondary_light
                )
            )
        }
    }
}