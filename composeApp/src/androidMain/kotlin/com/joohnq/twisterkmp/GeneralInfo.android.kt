package com.joohnq.twisterkmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

actual class GeneralInfo : GeneralInfoInterface {
    actual override val osType: OSType = OSType.ANDROID

    @Composable actual override fun deviceSize(): DeviceSize =
        DeviceSize(
            width = LocalConfiguration.current.screenWidthDp.dp,
            height = LocalConfiguration.current.screenHeightDp.dp
        )
}