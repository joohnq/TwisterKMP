package com.joohnq.twisterkmp.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.twisterkmp.presentation.game.GameScreen
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        MaterialTheme {
            Navigator(
                screen = GameScreen()
            )
        }
    }
}