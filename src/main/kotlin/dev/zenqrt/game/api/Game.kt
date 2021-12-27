package dev.zenqrt.game.api

import dev.zenqrt.game.api.phase.GamePhase
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import net.minestom.server.entity.Player

abstract class Game<T : GamePlayer>(private val gamePlayerHandler: GamePlayerHandler<T>) : GamePlayerHandler<T> by gamePlayerHandler {
    abstract val startingPhase: GamePhase

    fun broadcastMessage(component: Component) {
        broadcast { it.sendMessage(component) }
    }

    fun broadcastTitle(title: Title) {
        broadcast { it.showTitle(title) }
    }

    fun broadcastActionBar(component: Component) {
        broadcast { it.sendActionBar(component) }
    }

    fun broadcast(handler: (Player) -> Unit) {
        gamePlayers.keys.forEach(handler)
    }
}