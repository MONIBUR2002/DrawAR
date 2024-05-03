package com.moniapps.drawar.ui.components

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moniapps.drawar.R

@Composable
fun AppLibraryCard(
    cardName: String,
    image: Painter
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(
                top = 12.dp,
                bottom = 12.dp,
                start = 16.dp,
                end = 16.dp
            )
            .clickable { /*TODO*/ }, elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 80.dp, height = 90.dp)
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                    .clip(RoundedCornerShape(10.dp))

            )
            Text(
                text = cardName,
                modifier = Modifier.padding(start = 12.dp),
                fontSize = 20.sp,
            )
        }
    }
}

@Preview
@Composable
private fun AppLibraryCardPreview() {
    AppLibraryCard(
        cardName = "Anime",
        image = painterResource(id = R.drawable.luffy)
    )
}