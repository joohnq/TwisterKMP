package com.joohnq.twisterkmp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import twisterkmp.composeapp.generated.resources.Res
import twisterkmp.composeapp.generated.resources.ic_pause
import twisterkmp.composeapp.generated.resources.pause

@Composable
fun GameTopBar(
    padding: PaddingValues,
    score: Int,
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .background(color = Color.White.copy(alpha = 0.9f))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .padding(top = padding.calculateTopPadding()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = score.toString(), fontSize = 24.sp)
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