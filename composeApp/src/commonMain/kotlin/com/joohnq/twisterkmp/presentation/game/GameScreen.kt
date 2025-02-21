package com.joohnq.twisterkmp.presentation.game

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import com.joohnq.twisterkmp.presentation.component.LoseDialog
import com.joohnq.twisterkmp.viewmodel.Intent
import com.joohnq.twisterkmp.viewmodel.ViewModel
import org.koin.compose.koinInject

class GameScreen : Screen {
    @Composable
    override fun Content() {
        val snackBarHostState = remember { SnackbarHostState() }
        val viewModel: ViewModel = koinInject()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.onEvent(Intent.SelectRandomColor)
            viewModel.onEvent(Intent.GenerateBallStages)
        }

        if (state.lose) {
            LoseDialog(
                onDismissRequest = { viewModel.onEvent(Intent.SetLoseState(false)) },
                onRestart = { viewModel.onEvent(Intent.RestartGame) },
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
