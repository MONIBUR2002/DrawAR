package com.moniapps.drawar.ui.screens


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.moniapps.drawar.ui.components.EditCard
import com.moniapps.drawar.viewmodel.CameraScreenViewModel
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraScreen(
    viewModel: CameraScreenViewModel
) {
    


            viewModel.ImagePicker()



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
var cameraLaunchedCount by remember {
    mutableIntStateOf(0)
}

    //Launch Camera
    LaunchedEffect(lensFacing) {

        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview)
        preview.setSurfaceProvider(previewView.surfaceProvider)
        Log.d("Camera Launched", "CameraScreen: Camera Launched ${++cameraLaunchedCount}")
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())
        AsyncImage(
            model = viewModel.selectedImageUri,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
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

        EditCard(
            imageOpacity = viewModel.imageOpacity,
            onOpacityChange = {
                viewModel.imageOpacityChanged(it)
            },
            locked = viewModel.isImageMovable,
            onLockClicked = {
                viewModel.isImageMovable = !viewModel.isImageMovable
            }
        )

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

@Preview
@Composable
private fun CameraScreenPreview() {

}
