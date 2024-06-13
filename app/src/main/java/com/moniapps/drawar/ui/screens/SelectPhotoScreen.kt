package com.moniapps.drawar.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.moniapps.drawar.R
import com.moniapps.drawar.navigation.ScreenGraph
import com.moniapps.drawar.ui.components.AppLibraryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPhotoScreen(
    navHostController: NavHostController,
    direction: String
) {

    Column {
        TopAppBar(
            title = { Text(text = "Select", modifier = Modifier.padding(start = 20.dp)) },
            navigationIcon = {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(
                        modifier = Modifier.padding(start = 8.dp),
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }

            }
        )
        val context = LocalContext.current
        Column {
            Box {
                Column {
                    Box(
                        modifier = Modifier
                    ) {
                        Column {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 16.dp,
                                        start = 16.dp,
                                        end = 16.dp,
                                        bottom = 8.dp
                                    ).clickable {
                                        when(direction){
                                            "TraceOnCamera" -> navHostController.navigate(ScreenGraph.CameraScreen.route)
                                            "TraceOnPhone" -> navHostController.navigate(ScreenGraph.TraceOnPhoneScreen.route)
                                        }
                                    }
                                , elevation = CardDefaults.cardElevation(12.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.library),
                                        contentDescription = "Library",
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .size(50.dp)
                                    )
                                    Text(text = "Take from library")
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 8.dp,
                                        start = 16.dp,
                                        end = 16.dp,
                                        bottom = 16.dp
                                    )
                                    .clickable {
                                        Toast
                                            .makeText(
                                                context,
                                                "Galary touch",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()


                                    }, elevation = CardDefaults.cardElevation(12.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.internet),
                                        contentDescription = "Library",
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .size(50.dp)
                                    )
                                    Text(text = "Take from Internet")
                                }
                            }

                        }

                    }

                }
            }
            Box(modifier = Modifier) {
                Column {
                    Text(
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                        text = "App Library",

                        )
                    LazyColumn {
                        item {
                            AppLibraryCard(
                                cardName = "Anime",
                                image = painterResource(id = R.drawable.luffy)
                            )
                            AppLibraryCard(
                                cardName = "Nature",
                                image = painterResource(id = R.drawable.library)
                            )
                            AppLibraryCard(
                                cardName = "Anime",
                                image = painterResource(id = R.drawable.luffy)
                            )
                            AppLibraryCard(
                                cardName = "Anime",
                                image = painterResource(id = R.drawable.luffy)
                            )
                            AppLibraryCard(
                                cardName = "Anime",
                                image = painterResource(id = R.drawable.luffy)
                            )
                            AppLibraryCard(
                                cardName = "Anime",
                                image = painterResource(id = R.drawable.luffy)
                            )

                        }
                    }
                }


            }
        }
    }


}

