package com.joohnq.twisterkmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import twisterkmp.composeapp.generated.resources.Res
import twisterkmp.composeapp.generated.resources.ic_pause
import twisterkmp.composeapp.generated.resources.pause

@Composable
fun GameUI(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    state: TwisterState,
    onEvent: (TwisterIntent) -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { padding ->
        BoxWithConstraints {
            LazyColumn(
                reverseLayout = true,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).height(
                    maxHeight - (maxWidth / 2) + 60.dp
                ),
                verticalArrangement = Arrangement.spacedBy(
                    10.dp,
                    alignment = Alignment.Bottom
                ),
            ) {
                itemsIndexed(state.items, key = { i, _ -> i }) { i, balls ->
                    val enabled = i == 0 && !state.lose
                    BallsRow(balls, enabled) { clickedBall ->
                        if (clickedBall == state.currentColor) {
                            onEvent(TwisterIntent.RemoveLastItem)
                            onEvent(TwisterIntent.UpdateScore(1))
                        } else {
                            onEvent(TwisterIntent.UpdateLoseState(true))
                        }
                    }
                }
            }
            val carouselOffset = maxHeight - (maxWidth / 2) + 30.dp

            Box(
                modifier = Modifier
                    .size(maxWidth)
                    .offset(y = carouselOffset)
                    .padding(vertical = 40.dp)
                    .background(color = Color(0xFF545656))
                    .padding(horizontal = 40.dp),
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
        Box(
            modifier = Modifier.fillMaxWidth()
                .background(color = Color.White.copy(alpha = 0.9f))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp).padding(top = padding.calculateTopPadding()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = state.score.toString(), fontSize = 24.sp)
                Text(text = "5.00", fontSize = 24.sp)
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_pause),
                        contentDescription = stringResource(Res.string.pause),
                        tint = Color.DarkGray,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

class GameScreen : Screen {
    @Composable
    override fun Content() {
        val snackBarHostState = remember { SnackbarHostState() }
        val viewModel: TwisterViewModel = koinViewModel()
        val state by viewModel.state.collectAsState()
        val lose by derivedStateOf { state.lose }
        val isEmpty by derivedStateOf { state.items.isEmpty() }

        LaunchedEffect(Unit) {
            viewModel.onEvent(TwisterIntent.ChangeColor)
            viewModel.onEvent(TwisterIntent.FillListBalls)
        }

        if (isEmpty) {
            LaunchedEffect(Unit) {
                viewModel.onEvent(TwisterIntent.Restart)
            }
        }

        if (lose) {
            LoseDialog(
                onDismissRequest = { viewModel.onEvent(TwisterIntent.UpdateLoseState(false)) },
                onRestart = { viewModel.onEvent(TwisterIntent.Restart) },
                onGoToHome = {}
            )
        }

        GameUI(
            snackBarHostState = snackBarHostState,
            onEvent = viewModel::onEvent,
            state = state,
        )
    }
}
