package com.moniapps.drawar.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.moniapps.drawar.R

@Composable
fun MainScreen(
    navHostController: NavHostController
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Box {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "AR Drawing",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = null,
                            Modifier.size(32.dp)
                        )
                    }
                }

            }
            Box(
                modifier = Modifier.fillMaxWidth(),

                ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    //Camera Button
                    OutlinedButton(
                        onClick = { navHostController.navigate("Select_Photo_Screen") },
                        shape = RoundedCornerShape(11.dp),
                        modifier = Modifier.size(width = 160.dp, height = 120.dp),
                        border = BorderStroke(2.dp, color = Color.Black)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.camera),
                                contentDescription = "camera",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                text = "Trace with Camera",
                                modifier = Modifier.padding(top = 4.dp),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    // Trace on phone button
                    OutlinedButton(
                        onClick = {  navHostController.navigate("Select_Photo_Screen") },
                        shape = RoundedCornerShape(11.dp),
                        modifier = Modifier.size(width = 160.dp, height = 120.dp),
                        border = BorderStroke(2.dp, color = Color.Black)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pencil),
                                contentDescription = "Trace on phone",
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                text = "Trace on phone",
                                modifier = Modifier.padding(top = 4.dp),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center

                            )
                        }
                    }
                }
                

            }
            Box{
                Text(text = "Your creation")
                LazyRow {

                }
            }
            Box{
                Text(text = "Trending")
                LazyRow {

                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    val context = LocalContext.current
    MainScreen(navHostController = NavHostController(context))
}