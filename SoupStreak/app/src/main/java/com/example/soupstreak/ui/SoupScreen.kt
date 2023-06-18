package com.example.soupstreak.ui

import SoupViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soupstreak.R
import com.example.soupstreak.ui.theme.SoupStreakTheme



@Composable
fun SoupScreen(viewModel: SoupViewModel = viewModel()) {
    val countState = viewModel.count
    val maxCountState = viewModel.maxCount
    val imagePainter: Painter = painterResource(R.drawable.soup_image)
    val openDialog = remember { mutableStateOf(false) }

    Column(
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
            modifier = Modifier.padding(24.dp).clickable(
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
                Text(
                    text = "X",
                    fontSize = 40.sp
                )
            }
            Button(
                onClick = { viewModel.incrementCount() },

                ) {
                Text(
                    text = "+",
                    fontSize = 40.sp
                )
            }
        }
        Button(
            onClick = { viewModel.resetMaxCount() }
        ) {
            Text(
                text = "Reset",
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
    
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            icon = { Icon(Icons.Filled.Favorite, contentDescription = null)},
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