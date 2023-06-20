package com.example.soupstreak.ui

import SoupViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soupstreak.R
import com.example.soupstreak.ui.theme.DarkGreen
import com.example.soupstreak.ui.theme.LightGreen
import com.example.soupstreak.ui.theme.LightGrey
import com.example.soupstreak.ui.theme.SoupStreakTheme
import java.util.Date


@Composable
fun SoupScreen(viewModel: SoupViewModel = viewModel()) {
    val countState = viewModel.count
    val maxCountState = viewModel.maxCount
    val imagePainter: Painter = painterResource(R.drawable.soup_image)
    val openDialog = remember { mutableStateOf(false) }

    Box {
        Column(
            modifier = Modifier.background(LightGrey)
        ) {
            Image(
                painter = imagePainter,
                contentDescription = "Bowl of soup",
                modifier = Modifier
                    .weight(1f)
                    .background(Color.DarkGray)
                    .padding(10.dp)
            )
            Box(modifier = Modifier.weight(2f))
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Spacer(
                Modifier.weight(1f)
            )
            Card(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth().weight(4f)
                ) {
                    Text(
                        text = "Soup Streak!",
                        fontSize = 40.sp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    Box {
                        Card(
                            modifier = Modifier
                                .size(100.dp)
                        ) {

                        }
                        Text(
                            text = "${countState.value}",
                            fontSize = 40.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center).drawBehind { drawCircle(color = LightGreen, radius = this.size.maxDimension) }
                        )
                    }
                    Text(
                        text = if (countState.value == 1) "day" else "days",
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = "Most recent: ${Date()}",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,

                    )

                }
                Box(modifier = Modifier.fillMaxSize().background(color = DarkGreen).weight(1f)) {
                    Text(
                        text = " Max streak: ${maxCountState.value} ${if (maxCountState.value == 1) "day" else "days"}",
                        fontSize = 30.sp,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                Button(
                    onClick = { viewModel.resetCount() },

                    ) {
                    Icon(Icons.Filled.Refresh, contentDescription = null)
                    Text(
                        text = "Reset",
                        fontSize = 14.sp
                    )
                }

                Button(
                    onClick = { viewModel.incrementCount() },

                    ) {

                    Icon(Icons.Filled.Add, contentDescription = null)

                    Text(
                        text = "Increase",
                        fontSize = 14.sp
                    )
                }

            }
        }

    }


    /*Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Soup Streak!",
            fontSize = 40.sp,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(
            text = " Max streak: ${maxCountState.value} ${if (maxCountState.value == 1) "day" else "days"}",
            fontSize = 16.sp
        )
        Image(
            painter = imagePainter,
            contentDescription = "Bowl of soup",
            modifier = Modifier
                .padding(24.dp)
                .clickable(
                    onClick = {
                        openDialog.value = true
                    },
                    onClickLabel = "Open reset menu"
                )
        )
        Text(
            text = " ${countState.value}",
            fontSize = 40.sp
        )
        Text(
            text = if (countState.value == 1) "day" else "days",
            modifier = Modifier.padding(16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Button(
                onClick = { viewModel.resetCount() },

                ) {
                Icon(Icons.Filled.Refresh, contentDescription = null)
                Text(
                    text = "Reset",
                    fontSize = 14.sp
                )
            }

            Button(
                onClick = { viewModel.incrementCount() },

                ) {

                Icon(Icons.Filled.Add, contentDescription = null)

                Text(
                    text = "Increase",
                    fontSize = 14.sp
                )
            }

        }

       *//* Button(
            onClick = { viewModel.resetMaxCount() }
        ) {
            Text(
                text = "Reset",
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }*//*
    }*/
    
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            icon = { Icon(Icons.Filled.Delete , contentDescription = null)},
            title = {
                Text(text = "Reset streak")
            },
            text = {
                Text("Are you sure you want to reset your max streak?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.resetMaxCount()
                        openDialog.value = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )

    }
}



@Preview(showBackground = true)
@Composable
fun SoupScreenPreview() {
    SoupStreakTheme {
        SoupScreen()
    }
}