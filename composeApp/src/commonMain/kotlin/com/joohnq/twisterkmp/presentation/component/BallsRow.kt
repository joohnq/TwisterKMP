package com.joohnq.twisterkmp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.twisterkmp.domain.entity.Ball
import androidx.compose.runtime.getValue

@Composable
fun BallBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isClicked by interactionSource.collectIsPressedAsState()

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
    ){
        if(isClicked)
            PulseAnimation(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
    }
}

@Composable
fun BallsRow(balls: List<Ball>, onClick: (Ball) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
        balls.forEachIndexed { i, ball ->
            BallBox(
                modifier = Modifier.weight(1f)
                    .background(color = ball.color),
                onClick = { onClick(ball) },
            )
        }
    }
}