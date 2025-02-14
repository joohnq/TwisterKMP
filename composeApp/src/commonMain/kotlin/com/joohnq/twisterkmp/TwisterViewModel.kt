package com.joohnq.twisterkmp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TwisterState(
    val rowCount: Int = 10,
    val items: List<List<Ball>> = emptyList(),
    val currentColor: Ball = Ball.Red,
    val currentItem: Int = rowCount - 1,
    val lose: Boolean = false,
    val endRound: Boolean = false,
    val score: Int = 0,
    val balls: List<Ball> = Ball.getAll(),
    val stages: Map<Int, List<List<Ball>>> = emptyMap()
)

sealed class TwisterIntent {
    data class UpdateLoseState(val state: Boolean) : TwisterIntent()
    data object FillInitialListBalls : TwisterIntent()
    data object FillListBalls : TwisterIntent()
    data object ClearItems : TwisterIntent()
    data object Restart : TwisterIntent()
    data object RemoveLastItem : TwisterIntent()
    data object ChangeColor : TwisterIntent()
    data class ChangeEndRound(val value: Boolean) : TwisterIntent()
    data class UpdateCurrentItem(val item: Int) : TwisterIntent()
    data class UpdateScore(val value: Int) : TwisterIntent()
}

class TwisterViewModel : ViewModel() {
    private val _state: MutableStateFlow<TwisterState> = MutableStateFlow(TwisterState())
    val state: StateFlow<TwisterState> = _state.asStateFlow()

    fun onEvent(intent: TwisterIntent) {
        when (intent) {
            is TwisterIntent.Restart -> {
                fillListBalls()
                updateLoseState(false)
                changeColor()
                changeEndRound(false)
            }

            TwisterIntent.FillListBalls -> fillListBalls()
            is TwisterIntent.UpdateLoseState -> updateLoseState(intent.state)
            TwisterIntent.ClearItems -> clearItems()
            TwisterIntent.RemoveLastItem -> removeLastItem()
            is TwisterIntent.UpdateCurrentItem -> updateCurrentItem(intent.item)
            TwisterIntent.ChangeColor -> changeColor()
            is TwisterIntent.ChangeEndRound -> changeEndRound(intent.value)
            is TwisterIntent.UpdateScore -> updateScore(intent.value)
            TwisterIntent.FillInitialListBalls -> fillInitialListBalls()
        }
    }

    private fun updateScore(value: Int) {
        _state.update {
            it.copy(
                score = it.score + value
            )
        }
    }

    private fun changeEndRound(value: Boolean) {
        _state.update {
            it.copy(
                endRound = value
            )
        }
    }

    private fun changeColor() {
        val i = (0 until state.value.balls.size).random()
        _state.update {
            it.copy(
                currentColor = state.value.balls[i]
            )
        }
    }

    private fun removeLastItem() {
        _state.update {
            it.copy(
                items = it.items.drop(1)
            )
        }
    }

    private fun clearItems() {
        _state.update {
            it.copy(
                items = emptyList()
            )
        }
    }

    private fun updateLoseState(state: Boolean) {
        _state.update {
            it.copy(
                lose = state
            )
        }
    }

    private fun updateCurrentItem(item: Int) {
        _state.update {
            it.copy(
                currentItem = item
            )
        }
    }

    private fun fillInitialListBalls() {
        val balls = List(state.value.rowCount) {
            state.value.balls.shuffled()
        }
        _state.update {
            it.copy(
                items = balls
            )
        }
    }

    private fun fillListBalls() {
        val balls1 = List(state.value.rowCount) { state.value.balls.shuffled() }
        val balls2 = List(state.value.rowCount) { state.value.balls.shuffled() }
        _state.update {
            it.copy(
                stages = mapOf(1 to balls1, 2 to balls2)
            )
        }
    }
}