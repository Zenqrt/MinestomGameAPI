package dev.zenqrt.game

import dev.zenqrt.game.handler.GamePlayerHandler
import dev.zenqrt.game.handler.TickHandler
import dev.zenqrt.game.timer.Tickable

open class Game(private val gamePlayerHandler: GamePlayerHandler,
                private val tickHandler: TickHandler) : Tickable, GamePlayerHandler by gamePlayerHandler {

    var offline = false
        private set

    override fun tick() {
        if(offline) return

        if(tickHandler.shouldStart()) {
            tickHandler.active = true
        }

        if(tickHandler.active) {
            tickHandler.tick()
        }
    }
}