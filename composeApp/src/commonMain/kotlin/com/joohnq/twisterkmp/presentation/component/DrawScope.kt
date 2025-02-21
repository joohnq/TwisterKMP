package com.joohnq.twisterkmp.presentation.component

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.joohnq.twisterkmp.domain.entity.Ball
import com.joohnq.twisterkmp.domain.mapper.toDegrees

fun DrawScope.drawSlice(
    sliceAngle: Float,
    rotation: Float,
    balls: List<Ball>,
) {
    val canvasCenter = Offset(size.width / 2, size.height / 2)
    val radius = (size.width - 70f) / 2

    for (i in balls.indices) {
        val startAngle = rotation + sliceAngle * i
        val ball = balls[i]

        drawArc(
            color = ball.color,
            startAngle = startAngle.toDegrees().toFloat(),
            sweepAngle = sliceAngle.toDegrees().toFloat(),
            useCenter = true,
            size = Size(radius * 2, radius * 2),
            topLeft = Offset(canvasCenter.x - radius, canvasCenter.y - radius)
        )

//        drawArc(
//            color = Color.White,
//            startAngle = startAngle.toDegrees().toFloat(),
//            sweepAngle = 5f,
//            useCenter = true,
//            size = Size(radius * 2, radius * 2),
//            topLeft = Offset(canvasCenter.x - radius, canvasCenter.y - radius)
//        )
    }
}

fun DrawScope.drawCenterCircle(radius: Float, backgroundColor: Color) {
    val center = Offset(size.width / 2, size.height / 2)

    drawCircle(
        color = backgroundColor,
        radius = radius,
        center = center
    )
}

fun DrawScope.drawCenterCircle(backgroundColor: Color) {
    val center = Offset(size.width / 2, size.height / 2)

    drawCircle(
        color = backgroundColor,
        center = center
    )
}
