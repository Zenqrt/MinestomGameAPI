package dev.zenqrt.game.api.phase.phase

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.event.GamePlayerJoinEvent
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode
import net.minestom.server.event.trait.CancellableEvent

interface PhaseTrait {
    fun handleTrait()
}

abstract class EventTrait(val eventNode: EventNode<Event>): PhaseTrait

open class CancelEventTrait<T : CancellableEvent>(eventNode: EventNode<Event>, private val eventBuilder: EventListener.Builder<T>) : EventTrait(eventNode) {
    override fun handleTrait() {
        eventNode.addListener(eventBuilder
            .handler { it.isCancelled = true }
            .build())
    }
}

class MaxPlayersEventTrait(eventNode: EventNode<Event>, eventBuilder: EventListener.Builder<GamePlayerJoinEvent>,
                           private val game: Game, private val maxPlayers: Int) : CancelEventTrait<GamePlayerJoinEvent>(eventNode, eventBuilder) {
    init {
        eventBuilder.filter { game.gamePlayers.size > maxPlayers }
    }

}