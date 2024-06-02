package com.moniapps.drawar.viewmodel

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraScreenViewModel : ViewModel() {
    private val _imageUri = MutableLiveData<Uri?>(null)
    val imageUri: LiveData<Uri?> = _imageUri

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
            Log.d("image_picker", "ImagePicker: Called")
        }
    }


    var imageOpacity by mutableFloatStateOf(0.5f)
    var scale by mutableFloatStateOf(1f)
    var rotation by mutableFloatStateOf(0f)
    var offset by mutableStateOf(Offset.Zero)
    var isImageMovable by mutableStateOf(true)

    fun imageOpacityChanged(opacity: Float) {
        imageOpacity = opacity
    }

}
