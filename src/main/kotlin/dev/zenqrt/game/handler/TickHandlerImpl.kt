package dev.zenqrt.game.handler

import dev.zenqrt.game.GameOptions
import dev.zenqrt.game.condition.StartCondition
import dev.zenqrt.game.condition.StartConditionImpl
import dev.zenqrt.game.phase.GameActivePhase
import dev.zenqrt.game.phase.TimerPhase
import dev.zenqrt.game.state.GameActiveState
import dev.zenqrt.game.state.GameState
import dev.zenqrt.game.state.TimerState

class TickHandlerImpl(options: GameOptions,
                      countdownPhase: TimerPhase,
                      gamePhase: GameActivePhase,
                      endingPhase: TimerPhase,
                      private val gamePlayerHandler: GamePlayerHandler = GamePlayerHandlerImpl(options),
                      private val startCondition: StartCondition = StartConditionImpl(options, gamePlayerHandler.players),
) : TickHandler, GamePlayerHandler by gamePlayerHandler {

    private val states = mutableListOf(TimerState(countdownPhase), GameActiveState(gamePhase), TimerState(endingPhase))
    private var currentState: GameState ?= null

    override fun tick(time: Long) {
        if(currentState == null && !startCondition.canStart()) return

        currentState!!.tick()

        if(currentState!!.shouldEnd()) {
            states.remove(currentState)

            currentState = if(states.size == 0) {
                players.clear()
                null
            } else {
                states[0]
            }
        }
    }

    override fun shouldEnd(): Boolean = currentState == null && states.size == 0
}