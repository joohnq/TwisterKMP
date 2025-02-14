package com.joohnq.twisterkmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import platform.UIKit.UIScreen

actual class GeneralInfo : GeneralInfoInterface {
    actual override val osType: OSType = OSType.IOS

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable actual override fun deviceSize(): DeviceSize =
        DeviceSize(
            width = LocalWindowInfo.current.containerSize.width.pxToPoint().dp,
            height = LocalWindowInfo.current.containerSize.height.pxToPoint().dp
        )
}

fun Int.pxToPoint(): Double = this.toDouble() / UIScreen.mainScreen.scale