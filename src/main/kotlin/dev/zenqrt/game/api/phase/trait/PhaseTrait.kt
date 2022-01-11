package dev.zenqrt.game.api.phase.trait

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import dev.zenqrt.game.api.event.GamePlayerJoinEvent
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.PlayerDisconnectEvent
import net.minestom.server.event.trait.CancellableEvent

interface PhaseTrait {
    fun handleTrait()
    fun endTrait() {}
}

abstract class EventTrait(val eventNode: EventNode<Event>): PhaseTrait

open class CancelEventTrait<T : CancellableEvent>(eventNode: EventNode<Event>, private val eventBuilder: EventListener.Builder<T>) : EventTrait(eventNode) {
    override fun handleTrait() {
        eventNode.addListener(eventBuilder
            .handler { it.isCancelled = true }
            .build())
    }
}

class MaxPlayerLimitCancelEventTrait<T : CancellableEvent>(eventNode: EventNode<Event>, eventBuilder: EventListener.Builder<T>,
                                     private val game: Game<out GamePlayer>, private val maxPlayers: Int) : CancelEventTrait<T>(eventNode, eventBuilder) {
    init {
        eventBuilder.filter { game.gamePlayers.size > maxPlayers }
    }
}

class RemovePlayerOnDisconnectEventTrait<T : PlayerDisconnectEvent>(eventNode: EventNode<Event>,
                                                                    private val eventBuilder: EventListener.Builder<T>,
                                                                    private val game: Game<GamePlayer>) : EventTrait(eventNode) {
    override fun handleTrait() {
        eventNode.addListener(eventBuilder.handler {
            game.gamePlayers[it.player]?.let { it1 -> game.removePlayer(it1, it.player, game) }
        }.build())
    }
}