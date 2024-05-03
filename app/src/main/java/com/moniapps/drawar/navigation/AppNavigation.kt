package com.moniapps.drawar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moniapps.drawar.ui.screens.MainScreen
import com.moniapps.drawar.ui.screens.SelectPhoto

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Main_Screen") {
        composable(route = "Main_Screen"){
            MainScreen(navController = navController)
        }
        composable(route = "Select_Photo_Screen"){
            SelectPhoto(navController = navController)
        }

    }
}