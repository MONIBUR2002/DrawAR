package com.moniapps.drawar.ui.components


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moniapps.drawar.R
import com.moniapps.drawar.ui.theme.GRAY
import com.moniapps.drawar.ui.theme.RED


@Composable
fun EditCard(
    imageOpacity: Float,
    onLockClicked: () -> Unit,
    onOpacityChange: (Float) -> Unit,
    onResetClicked: () -> Unit,
    locked: Boolean,
    modifier: Modifier
) {

    var expanded by remember { mutableStateOf(false) }
    val cardHeight = if (expanded) 120.dp else 60.dp
    Box(
        modifier.background(Color.White)
            .height(cardHeight).animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxSize(),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (expanded)
                ExpandableView(sliders = {
                    Slider(
                        value = imageOpacity,
                        onValueChange = { onOpacityChange(it) }
                    )
                }, title = "Opacity")

            Row(
                modifier = Modifier
                //.padding(start = 12.dp, end = 12.dp)
            ) {
                IconButton(
                    onClick = {
                        expanded = !expanded
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.opacity),
                        contentDescription = "Opacity icon",
                        tint = GRAY
                    )
                }
                IconButton(
                    onClick = {
                        onLockClicked()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = "Lock icon",
                        tint = if (locked) GRAY else RED
                    )
                }
                IconButton(
                    onClick = {
                       onResetClicked()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.reset),
                        contentDescription = "Reset Position",
                        tint = GRAY
                    )
                }
            }

        }
    }
}


@Composable
fun ExpandableView(
    sliders: @Composable () -> Unit,
    title: String,
) {
    Box(
        modifier = Modifier.size(width = 300.dp, height = 60.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, color = Color.Black)
            sliders()
        }
    }
}

@Preview
@Composable
private fun EditCardPreview() {
    EditCard(imageOpacity = 0.5f, onLockClicked = {}, onOpacityChange = {}, onResetClicked = {}, locked = false, modifier = Modifier)
}