package com.moniapps.drawar.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.moniapps.drawar.ui.components.EditCard
import com.moniapps.drawar.viewmodel.TraceOnPhoneViewModel

@Composable
fun TraceOnPhone(
    viewModel: TraceOnPhoneViewModel,
    navHostController: NavHostController
) {
    val imageUri by viewModel.imageUri.observeAsState()

    if (imageUri == null) viewModel.ImagePicker()
    BackHandler {
        navHostController.navigateUp()
        viewModel.makeImageUriNull()
    }
    //Image
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        if (viewModel.isImageMovable) {
            viewModel.scale *= zoomChange
            viewModel.rotation += rotationChange
            viewModel.offset += offsetChange
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        AsyncImage(
            model = imageUri,
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
            },
            modifier = Modifier,
            onResetClicked = {
                viewModel.onResetPosition()
            }
        )

    }
}