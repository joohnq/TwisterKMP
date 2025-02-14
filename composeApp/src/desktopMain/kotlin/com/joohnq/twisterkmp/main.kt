package com.joohnq.twisterkmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TwisterKMP",
    ) {
        App()
    }
}