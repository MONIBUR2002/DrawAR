package com.moniapps.drawar

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.moniapps.drawar.navigation.AppNavigation
import com.moniapps.drawar.ui.theme.DrawARTheme
import com.moniapps.drawar.viewmodel.CameraScreenViewModel
import com.moniapps.drawar.viewmodel.TraceOnPhoneViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val traceOnPhoneScreenViewModel by viewModels<TraceOnPhoneViewModel>()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContent {
            val cameraScreenViewModel: CameraScreenViewModel = hiltViewModel()

            val permissionState = rememberMultiplePermissionsState(
                permissions = listOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_MEDIA_IMAGES
                )
            )
            val navController = rememberNavController()

            DrawARTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GetPermissions(permissionStates = permissionState)
                    AppNavigation(
                        navHostController = navController,
                        cameraScreenViewModel = cameraScreenViewModel,
                        traceOnPhoneViewModel = traceOnPhoneScreenViewModel
                    )
                }
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GetPermissions(
    permissionStates: MultiplePermissionsState
){
    SideEffect {
        permissionStates.launchMultiplePermissionRequest()
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DrawARTheme {

    }
}
