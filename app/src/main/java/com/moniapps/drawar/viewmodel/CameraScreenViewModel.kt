package com.moniapps.drawar.viewmodel

import android.content.Context
import android.hardware.camera2.CameraManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.camera.core.Camera
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class CameraScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _imageUri = MutableLiveData<Uri?>(null)
    val imageUri: LiveData<Uri?> = _imageUri

    private var camera: Camera? = null


    var isFlashlightOn by mutableStateOf(false)

    fun toggleFlashlight() {


    }
    fun makeImageUriNull(){
        _imageUri.value = null
    }

    @Composable
    fun ImagePicker() {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                _imageUri.value = uri
            }
        )
        LaunchedEffect(Unit) {
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }


    var imageOpacity by mutableFloatStateOf(0.5f)
    var scale by mutableFloatStateOf(1f)
    var rotation by mutableFloatStateOf(0f)
    var offset by mutableStateOf(Offset.Zero)
    var isImageMovable by mutableStateOf(true)

    fun onResetPosition(){
        offset = Offset.Zero
    }

    fun imageOpacityChanged(opacity: Float) {
        imageOpacity = opacity
    }

}
