package com.joohnq.twisterkmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

data class DeviceSize(
    val width: Dp,
    val height: Dp
)

interface GeneralInfoInterface {
    val osType: OSType
    @Composable fun deviceSize(): DeviceSize
}

enum class OSType {
    IOS, ANDROID, DESKTOP
}

expect class GeneralInfo : GeneralInfoInterface {
    override val osType: OSType
    @Composable override fun deviceSize(): DeviceSize
}
