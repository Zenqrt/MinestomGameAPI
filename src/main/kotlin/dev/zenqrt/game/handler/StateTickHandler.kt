package dev.zenqrt.game.handler

import dev.zenqrt.game.GameOptions
import dev.zenqrt.game.phase.GameActivePhase
import dev.zenqrt.game.phase.TimerPhase
import dev.zenqrt.game.state.CountdownState
import dev.zenqrt.game.state.EndingState
import dev.zenqrt.game.state.GameActiveState
import dev.zenqrt.game.state.GameState
import java.util.*

class StateTickHandler(private val options: GameOptions,
                       countdownPhase: TimerPhase,
                       gamePhase: GameActivePhase,
                       endingPhase: TimerPhase,
                       private val gamePlayerHandler: GamePlayerHandler = GamePlayerHandlerImpl(options),
) : TickHandler(), GamePlayerHandler by gamePlayerHandler {

    private val states: Queue<GameState> = LinkedList(listOf(CountdownState(countdownPhase), GameActiveState(gamePhase), EndingState(endingPhase)))
    var currentState: GameState ?= states.poll()

    override fun shouldStart(): Boolean = players.size >= options.minPlayers

    override fun tick() {
        if(currentState == null) return
        currentState!!.tick()

        if(currentState!!.shouldEnd()) {
            currentState = states.poll()
        }
    }

    override fun shouldEnd(): Boolean = currentState == null && states.size == 0
}