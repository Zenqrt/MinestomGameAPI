package dev.zenqrt.game.christmas.phase.trait

import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.filter.GameFilter
import dev.zenqrt.game.api.phase.trait.PhaseTrait
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.utils.teleport
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode
import net.minestom.server.instance.Instance

class TeleportToSpawnPhaseTrait(private val eventNode: EventNode<Event>, private val game: ChristmasGame, private val instance: Instance, private val pos: Pos) : PhaseTrait {
    override fun handleTrait() {
        eventNode.addListener(EventListener.builder(GamePlayerPostJoinEvent::class.java)
            .filter(GameFilter(game))
            .handler { it.player.teleport(instance, pos) }
            .build())
    }
}