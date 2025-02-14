package com.joohnq.twisterkmp

import androidx.compose.ui.window.ComposeUIViewController
import com.joohnq.twisterkmp.di.KoinInitializer

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    App()
}