package com.moniapps.drawar.ui.screens


import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.moniapps.drawar.ui.components.EditCard
import com.moniapps.drawar.viewmodel.CameraScreenViewModel
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraScreen(
    viewModel: CameraScreenViewModel,
    navHostController: NavHostController
) {
    val imageUri by viewModel.imageUri.observeAsState()

    if (imageUri == null) viewModel.ImagePicker()
    BackHandler {
        navHostController.navigateUp()
        viewModel.makeImageUriNull()
    }


    //Camera
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val preview = androidx.camera.core.Preview.Builder().build()
    val previewView = remember {
        PreviewView(context)
    }

    //Image
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        if (viewModel.isImageMovable) {
            viewModel.scale *= zoomChange
            viewModel.rotation += rotationChange
            viewModel.offset += offsetChange
        }
    }

    //Launch Camera
    LaunchedEffect(lensFacing) {

        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview)
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    Surface(
        Modifier.fillMaxSize()
    ) {
        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())
        AsyncImage(
            model = imageUri,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .alpha(viewModel.imageOpacity)
                .graphicsLayer(
                    scaleX = viewModel.scale,
                    scaleY = viewModel.scale,
                    rotationZ = viewModel.rotation,
                    translationX = viewModel.offset.x,
                    translationY = viewModel.offset.y
                )
                .transformable(state = state)
        )

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom) {


            EditCard(
                imageOpacity = viewModel.imageOpacity,
                onOpacityChange = {
                    viewModel.imageOpacityChanged(it)
                },
                locked = viewModel.isImageMovable,
                onLockClicked = {
                    viewModel.isImageMovable = !viewModel.isImageMovable
                },
                modifier = Modifier.fillMaxWidth(),
                onResetClicked = {
                    viewModel.onResetPosition()
                }
            )
        }
    }
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
    suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.resume(cameraProvider.run { get() })
            }, ContextCompat.getMainExecutor(this))
        }
    }


