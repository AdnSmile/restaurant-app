package com.vvwxx.bangkit.restaurantapp

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vvwxx.bangkit.restaurantapp.ui.navigation.NavigationItem
import com.vvwxx.bangkit.restaurantapp.ui.navigation.Screen
import com.vvwxx.bangkit.restaurantapp.ui.screen.home.HomeScreen
import com.vvwxx.bangkit.restaurantapp.ui.theme.RestaurantAppTheme

@Composable
fun RestaurantApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailRestaurant.route) {
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
                // panggil home screen nanti disini
                HomeScreen(
                    navigateToDetail = {id ->
                        navController.navigate(Screen.DetailRestaurant.createRoute(id))
                    },
                    modifier = Modifier.background(color = MaterialTheme.colors.background)
                )
            }

            composable(Screen.Favorite.route) {
                // panggil favorite screen nanti disini
            }

            composable(Screen.Profile.route) {
                // panggil profile screen nanti disini
            }

            composable(
                route = Screen.DetailRestaurant.route,
                arguments = listOf(navArgument("id") { type = NavType.StringType} ),
            ) {
                val id = it.arguments?.getString("id") ?: ""
                // panggil detail screen nanti
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )

        BottomNavigation {
            navigationItem.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

private fun shareResto(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.resto_app))
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.resto_app)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun RestaurantAppPreview() {
    RestaurantAppTheme {
        RestaurantApp()
    }
}