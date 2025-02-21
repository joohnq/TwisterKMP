package com.joohnq.twisterkmp.domain.entity

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.StringResource
import twisterkmp.composeapp.generated.resources.Res
import twisterkmp.composeapp.generated.resources.blue
import twisterkmp.composeapp.generated.resources.green
import twisterkmp.composeapp.generated.resources.red
import twisterkmp.composeapp.generated.resources.yellow

sealed class Ball(
    val id: Int,
    val color: Color,
    val text: StringResource,
) {
    data object Red : Ball(id = RED_ID, color = Color.Companion.Red, text = Res.string.red)
    data object Blue : Ball(id = BLUE_ID, color = Color.Companion.Blue, text = Res.string.blue)
    data object Green : Ball(id = GREEN_ID, color = Color.Companion.Green, text = Res.string.green)
    data object Yellow :
        Ball(id = YELLOW_ID, color = Color.Companion.Yellow, text = Res.string.yellow)

    companion object {
        const val RED_ID = 0
        const val BLUE_ID = 1
        const val GREEN_ID = 2
        const val YELLOW_ID = 3

        fun getAll(): List<Ball> = listOf(Red, Blue, Green, Yellow)
    }
}