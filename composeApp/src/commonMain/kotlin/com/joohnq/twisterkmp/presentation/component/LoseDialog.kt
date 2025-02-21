package com.joohnq.twisterkmp.presentation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun LoseDialog(
    onDismissRequest: () -> Unit,
    onRestart: () -> Unit,
    onGoToHome: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = "Game Over")
        },
        text = {
            Text(text = "You want to restart")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onRestart()
                }
            ) {
                Text("Restart")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onGoToHome()
                }
            ) {
                Text("Home")
            }
        }
    )
}