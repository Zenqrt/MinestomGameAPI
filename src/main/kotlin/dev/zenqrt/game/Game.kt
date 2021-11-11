package dev.zenqrt.game

import dev.zenqrt.game.handler.GamePlayerHandler
import dev.zenqrt.game.handler.TickHandler
import net.minestom.server.Tickable

class Game(private val gamePlayerHandler: GamePlayerHandler,
           private val tickHandler: TickHandler) : Tickable, GamePlayerHandler by gamePlayerHandler {

    var offline = false
        private set

    override fun tick(time: Long) {
        if(offline) return
        tickHandler.tick(time)
    }

    fun isOffline(): Boolean = this.offline
}