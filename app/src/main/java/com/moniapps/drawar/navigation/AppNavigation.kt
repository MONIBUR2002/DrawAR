package com.moniapps.drawar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moniapps.drawar.ui.screens.CameraScreen
import com.moniapps.drawar.ui.screens.MainScreen
import com.moniapps.drawar.ui.screens.SelectPhotoScreen
import com.moniapps.drawar.ui.screens.TraceOnPhone
import com.moniapps.drawar.viewmodel.CameraScreenViewModel
import com.moniapps.drawar.viewmodel.TraceOnPhoneViewModel

@Composable
fun AppNavigation(
    navHostController: NavHostController,
    cameraScreenViewModel: CameraScreenViewModel,
    traceOnPhoneViewModel: TraceOnPhoneViewModel
) {

    NavHost(navController = navHostController, startDestination = ScreenGraph.MainScreen.route) {

        composable(
            route = ScreenGraph.MainScreen.route
        ) {
            MainScreen(navHostController = navHostController)
        }


        composable(route = ScreenGraph.SelectPhotoScreen.route + "/{direction}",
            arguments = listOf(
                navArgument("direction") {
                    type = NavType.StringType
                }
            )) {
            val direction = it.arguments!!.getString("direction")
            SelectPhotoScreen(
                navHostController = navHostController,
                direction = direction!!
            )
        }

        composable(
            route = ScreenGraph.CameraScreen.route
        ) {
            CameraScreen(cameraScreenViewModel, navHostController)
        }

        composable(
            route = ScreenGraph.TraceOnPhoneScreen.route
        ) {
            TraceOnPhone(traceOnPhoneViewModel, navHostController)
        }
    }

}
