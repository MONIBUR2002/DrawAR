package com.moniapps.drawar

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.moniapps.drawar.ui.theme.DrawARTheme

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val permissionState = rememberMultiplePermissionsState(
                permissions = listOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_MEDIA_IMAGES
                )
            )

            DrawARTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GetPermissions(permissionStates = permissionState)

                }
            }
        }
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
