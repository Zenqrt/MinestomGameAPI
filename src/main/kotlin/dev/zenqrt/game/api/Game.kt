package dev.zenqrt.game.api

import dev.zenqrt.game.api.phase.GamePhase
import net.minestom.server.adventure.audience.PacketGroupingAudience
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player

abstract class Game<T : GamePlayer>(private val gamePlayerHandler: GamePlayerHandler<T>): PacketGroupingAudience, GamePlayerHandler<T> by gamePlayerHandler {
    abstract val startingPhase: GamePhase

    fun startGame() {
        startingPhase.start()
    }

    fun broadcast(handler: (Player) -> Unit) {
        gamePlayers.keys.forEach(handler)
    }

    override fun getPlayers(): MutableCollection<Player> = mutableListOf<Player>().also { it.addAll(gamePlayers.keys) }
}