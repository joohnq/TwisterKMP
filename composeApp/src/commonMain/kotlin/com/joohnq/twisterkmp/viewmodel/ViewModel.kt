package com.joohnq.twisterkmp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.twisterkmp.domain.entity.Ball
import com.joohnq.twisterkmp.domain.mapper.currentKey
import com.joohnq.twisterkmp.domain.mapper.lastKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
    private val _state: MutableStateFlow<State> = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun onEvent(intent: Intent) {
        when (intent) {
            is Intent.RestartGame -> {
                resetStages()
                setLoseState(false)
                generateBallStages()
                selectRandomColor()
            }

            Intent.GenerateBallStages -> generateBallStages()
            is Intent.SetLoseState -> setLoseState(intent.state)
            Intent.RemoveCurrentRow -> removeCurrentRow()
            Intent.RemoveCurrentStage -> removeCurrentStage()
            Intent.AddStageInTheEnd -> addStageInTheEnd()
            Intent.SelectRandomColor -> selectRandomColor()
            is Intent.IncrementScore -> incrementScore(intent.value)
            is Intent.BallClick -> ballClick(intent.ball, intent.index)
        }
    }

    private fun resetStages() {
        _state.update {
            it.copy(stages = emptyMap())
        }
    }

    private fun ballClick(ball: Ball, index: Int) {
        if (ball != state.value.currentColor || index != 0) {
            onEvent(Intent.SetLoseState(true))
            return
        }

        onEvent(Intent.RemoveCurrentRow)
        onEvent(Intent.IncrementScore(1))
    }

    private fun incrementScore(value: Int) {
        _state.update {
            it.copy(
                score = it.score + value
            )
        }
    }

    private fun selectRandomColor() = viewModelScope.launch(Dispatchers.Default) {
        val ball = state.value.balls.random()
        _state.update {
            it.copy(
                currentColor = ball
            )
        }
    }

    private fun removeCurrentRow() = viewModelScope.launch(Dispatchers.Default) {
        _state.update { state ->
            val stages = state.stages.toMutableMap()

            val currentList = stages[state.stages.currentKey()]!!
            if (currentList.size == 1) {
                onEvent(Intent.RemoveCurrentStage)
                onEvent(Intent.AddStageInTheEnd)
                onEvent(Intent.SelectRandomColor)
            } else {
                stages[state.stages.currentKey()] = currentList.drop(1)
            }
            state.copy(stages = stages)
        }
    }

    private fun removeCurrentStage() = viewModelScope.launch(Dispatchers.Default) {
        _state.update { state ->
            val firstKey = state.stages.currentKey()
            val stages = state.stages.toMutableMap()
            stages.remove(firstKey)
            state.copy(stages = stages)
        }
    }

    private fun setLoseState(state: Boolean) {
        _state.update {
            it.copy(
                lose = state
            )
        }
    }

    private fun addStageInTheEnd() = viewModelScope.launch(Dispatchers.Default) {
        val balls = List(state.value.rowCount){ state.value.balls.shuffled() }
        _state.update {
            it.copy(
                stages = it.stages + (it.stages.lastKey() + 1 to balls),
                rowCount = it.rowCount + 1
            )
        }
    }

    private fun generateBallStages() {
        addStageInTheEnd()
        addStageInTheEnd()
    }
}