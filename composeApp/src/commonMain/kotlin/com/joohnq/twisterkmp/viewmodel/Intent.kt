package com.joohnq.twisterkmp.viewmodel

import com.joohnq.twisterkmp.domain.entity.Ball

sealed interface Intent {
    data class SetLoseState(val state: Boolean) : Intent
    data object GenerateBallStages : Intent
    data object RestartGame : Intent
    data object RemoveCurrentRow : Intent
    data object AddStageInTheEnd : Intent
    data object SelectRandomColor : Intent
    data object RemoveCurrentStage : Intent
    data class IncrementScore(val value: Int) : Intent
    data class BallClick(val ball: Ball, val index: Int) : Intent
}