package com.joohnq.twisterkmp

import androidx.compose.ui.window.ComposeUIViewController
import com.joohnq.twisterkmp.di.KoinInitializer
import com.joohnq.twisterkmp.presentation.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    App()
}