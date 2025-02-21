package com.joohnq.twisterkmp.viewmodel

import com.joohnq.twisterkmp.domain.entity.Ball
import com.joohnq.twisterkmp.domain.entity.Ball.Companion.getAll

data class State(
    val rowCount: Int = 10,
    val currentColor: Ball = Ball.Red,
    val lose: Boolean = false,
    val score: Int = 0,
    val balls: List<Ball> = getAll(),
    val stages: Map<Int, List<List<Ball>>> = emptyMap(),
)