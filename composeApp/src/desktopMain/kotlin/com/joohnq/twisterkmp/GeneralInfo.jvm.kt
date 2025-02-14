package com.joohnq.twisterkmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

actual class GeneralInfo : GeneralInfoInterface {
    actual override val osType: OSType = OSType.DESKTOP
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable actual override fun deviceSize(): DeviceSize = DeviceSize(
        width = LocalWindowInfo.current.containerSize.width.dp,
        height = LocalWindowInfo.current.containerSize.height.dp
    )
}