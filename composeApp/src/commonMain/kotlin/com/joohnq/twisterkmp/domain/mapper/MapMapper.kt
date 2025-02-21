package com.joohnq.twisterkmp.domain.mapper

import com.joohnq.twisterkmp.domain.entity.Ball

fun Map<Int, List<List<Ball>>>.currentKey(): Int = keys.firstOrNull() ?: 0
fun Map<Int, List<List<Ball>>>.lastKey(): Int = keys.lastOrNull() ?: 0