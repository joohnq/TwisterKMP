package com.joohnq.twisterkmp.presentation.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joohnq.twisterkmp.domain.entity.Ball
import com.joohnq.twisterkmp.presentation.component.BallsRow
import com.joohnq.twisterkmp.presentation.component.GameTopBar
import com.joohnq.twisterkmp.viewmodel.Intent
import com.joohnq.twisterkmp.viewmodel.State
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun GameUI(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    state: State,
    onEvent: (Intent) -> Unit = {},
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { padding ->
        Column {
            LazyColumn(
                userScrollEnabled = false,
                reverseLayout = true,
                modifier = Modifier.fillMaxWidth()
                    .weight(4f),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Bottom),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                for (key in state.stages.keys) {
                    val balls = state.stages[key] ?: emptyList()
                    itemsIndexed(balls) { i, balls ->
                        BallsRow(balls) { ball ->
                            onEvent(Intent.BallClick(ball, i))
                        }
                    }
                    if (key != state.stages.keys.last())
                        item {
                            Box(
                                modifier = Modifier
                                    .background(color = Color(0xFF606161))
                                    .fillMaxWidth()
                                    .height(100.dp)
                            )
                        }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color(0xff403f3f)),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = stringResource(state.currentColor.text),
                    color = Color.White,
                    fontSize = 30.sp
                )
//                Roulette(state.balls)
            }
        }
        GameTopBar(
            padding = padding,
            score = state.score
        )
    }
}


@Preview
@Composable
fun GameUIPreview() {
    GameUI(
        state = State(
            stages = mapOf(
                1 to listOf(
                    listOf(Ball.Red, Ball.Blue, Ball.Green, Ball.Yellow),
                    listOf(Ball.Blue, Ball.Red, Ball.Yellow, Ball.Green),
                    listOf(Ball.Yellow, Ball.Green, Ball.Blue, Ball.Red),
                    listOf(Ball.Green, Ball.Yellow, Ball.Red, Ball.Blue),
                ),
                2 to listOf(
                    listOf(Ball.Red, Ball.Blue, Ball.Green, Ball.Yellow),
                    listOf(Ball.Blue, Ball.Red, Ball.Yellow, Ball.Green),
                    listOf(Ball.Yellow, Ball.Green, Ball.Blue, Ball.Red),
                    listOf(Ball.Green, Ball.Yellow, Ball.Red, Ball.Blue),
                )
            )
        )
    )
}