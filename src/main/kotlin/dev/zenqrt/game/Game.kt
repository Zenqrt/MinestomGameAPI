package dev.zenqrt.game

import dev.zenqrt.game.phase.GameActivePhase
import dev.zenqrt.game.phase.TimerPhase
import dev.zenqrt.game.state.GameActiveState
import dev.zenqrt.game.state.GameState
import dev.zenqrt.game.state.TimerState
import net.minestom.server.Tickable

class Game(val options: GameOptions,
           countdownPhase: TimerPhase,
           gamePhase: GameActivePhase,
           endingPhase: TimerPhase) : Tickable {

    val players = mutableListOf<GamePlayer>()

    private val states = mutableListOf(TimerState(countdownPhase), GameActiveState(gamePhase), TimerState(endingPhase))
    private var currentState: GameState ?= null

    private fun canStart(): Boolean = this.players.size >= options.minPlayers

    override fun tick(time: Long) {
        if(currentState == null) {
            if(states.size == 0 || !canStart()) return
        } else {
            currentState!!.tick()

            if(currentState!!.shouldEnd()) {
                states.remove(currentState)

                if(states.size == 0) {
                    players.clear()
                } else {
                    currentState = states[0]
                }
            }
        }
    }
}