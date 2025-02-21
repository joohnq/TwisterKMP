package com.joohnq.twisterkmp.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.joohnq.twisterkmp.domain.entity.Ball
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun Roulette(balls: List<Ball>) {
    val list = balls + balls
    val totalSlices = list.size
    val circumference = 360f * (PI / 180)
    val slice = circumference / totalSlices
    val aQuarter = (circumference / 4 - slice / 2).toFloat()
    var rotation by rememberSaveable { mutableStateOf(0f) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(onDragEnd = {
                    val i = rotation % circumference
                    val middle = ((i / slice).roundToInt()) * slice
                    rotation = middle.toFloat()
                }) { change, dragAmount ->
                    rotation += dragAmount.x * 0.005f
                    val percent = (rotation - aQuarter) % circumference
                    val differenceAngle = percent.absoluteValue * 360 / circumference
                    val angle = if (percent < 0) differenceAngle else 360 - differenceAngle
                    val index = ((angle / 360) * totalSlices).roundToInt() % totalSlices
                    change.consume()
                }
            }
    ) {
        drawCenterCircle(backgroundColor = Color.White)
        drawSlice(
            slice.toFloat(),
            rotation,
            list
        )
        drawCenterCircle(radius = size.width / 6, backgroundColor = Color.White)
    }
}

@Preview
@Composable
fun RoulettePreview() {
    Roulette(
        balls = listOf(
            Ball.Blue,
            Ball.Yellow,
            Ball.Red,
            Ball.Green,
        )
    )
}