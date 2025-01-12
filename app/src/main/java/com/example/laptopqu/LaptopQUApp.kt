package com.example.laptopqu

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laptopqu.data.LaptopRepository
import com.example.laptopqu.ui.navigation.NavigationItem
import com.example.laptopqu.ui.navigation.Screen
import com.example.laptopqu.ui.screen.detail.DetailScreen
import com.example.laptopqu.ui.screen.favorite.FavoriteScreen
import com.example.laptopqu.ui.screen.home.HomeScreen
import com.example.laptopqu.ui.screen.profile.ProfileScreen

@Composable
fun LaptopQUApp(
    modifier: Modifier = Modifier,
    viewModel: LaptopQUViewModel = viewModel(factory = ViewModelFactory(LaptopRepository())),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(viewModel = viewModel, navController = navController)
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(viewModel = viewModel, navController = navController)
            }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.DetailLaptop.route) { backStackEntry ->
                val laptopId = backStackEntry.arguments?.getString("laptopId")?.toInt() ?: 0
                DetailScreen(laptopId = laptopId, navController = navController)
            }
        }
    }
}


@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        val navigationItems = listOf(
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

        navigationItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = false,
                onClick = { navController.navigate(item.screen.route) }
            )
        }
    }
}
