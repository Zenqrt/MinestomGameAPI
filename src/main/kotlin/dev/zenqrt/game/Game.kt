package dev.zenqrt.game

import dev.zenqrt.game.handler.GamePlayerHandler
import dev.zenqrt.game.handler.GamePlayerHandlerImpl
import dev.zenqrt.game.phase.GameActivePhase
import dev.zenqrt.game.phase.TimerPhase
import dev.zenqrt.game.state.GameActiveState
import dev.zenqrt.game.state.GameState
import dev.zenqrt.game.state.TimerState
import net.minestom.server.Tickable

class Game(val options: GameOptions,
           countdownPhase: TimerPhase,
           gamePhase: GameActivePhase,
           endingPhase: TimerPhase,
           private val gamePlayerHandler: GamePlayerHandler = GamePlayerHandlerImpl()) : Tickable {
    private var offline = false

    private val states = mutableListOf(TimerState(countdownPhase), GameActiveState(gamePhase), TimerState(endingPhase))
    private var currentState: GameState ?= null

    private val players = mutableListOf<GamePlayer>()

    override fun tick(time: Long) {
        if(offline) return
        if(currentState == null) {
            if(states.size == 0) {
                offline = true
                return
            }
            if(!canStart()) return
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

    fun join(gamePlayer: GamePlayer): Boolean {
        val success = players.add(gamePlayer)
        if(success) {
            gamePlayerHandler.join(gamePlayer)
            return true
        }
        return false
    }

    fun leave(gamePlayer: GamePlayer): Boolean {
        val success = players.remove(gamePlayer)
        if(success) {
            gamePlayerHandler.leave(gamePlayer)
            return true
        }
        return false
    }

    fun isOffline(): Boolean = this.offline
    private fun canStart(): Boolean = this.players.size >= options.minPlayers
}