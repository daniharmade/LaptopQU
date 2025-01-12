package com.example.laptopqu.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laptopqu.LaptopQUViewModel
import com.example.laptopqu.ViewModelFactory
import com.example.laptopqu.data.LaptopRepository
import com.example.laptopqu.ui.components.LaptopList

@Composable
fun HomeScreen(
    viewModel: LaptopQUViewModel = viewModel(factory = ViewModelFactory(LaptopRepository())),
    navController: NavHostController
) {
    LaptopList(viewModel, navController)
}
