package com.moniapps.drawar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moniapps.drawar.ui.screens.CameraScreen
import com.moniapps.drawar.ui.screens.MainScreen
import com.moniapps.drawar.ui.screens.SelectPhotoScreen
import com.moniapps.drawar.viewmodel.CameraScreenViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    cameraScreenViewModel: CameraScreenViewModel
) {

    NavHost(navController = navController, startDestination = ScreenGraph.MainScreen.route) {
        composable(route = ScreenGraph.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = ScreenGraph.SelectPhotoScreen.route) {
            SelectPhotoScreen(navController = navController)
        }
        composable(
            route = ScreenGraph.CameraScreen.route
        ) {

            CameraScreen(cameraScreenViewModel)

        }
    }

}
