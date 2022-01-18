package dev.zenqrt.game.christmas.phase.trait

import dev.zenqrt.game.api.event.filter.GamePlayerFilter
import dev.zenqrt.game.api.phase.trait.PhaseTrait
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.game.ChristmasGamePlayer
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.leaderboard.Leaderboard
import dev.zenqrt.game.christmas.registry.WorkstationRegistryService
import dev.zenqrt.game.christmas.workstation.Workstation
import dev.zenqrt.game.christmas.workstation.WorkstationInteractionBox
import dev.zenqrt.game.christmas.workstation.handler.*
import dev.zenqrt.game.christmas.workstation.handler.impl.WorkstationHandlerImpl
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.minestom.server.coordinate.Vec
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.PlayerBlockInteractEvent
import world.cepi.kstom.event.listen
import java.io.File

class WorkstationPhaseTrait(private val eventNode: EventNode<Event>, private val game: ChristmasGame) : PhaseTrait {
    override fun handleTrait() {
        eventNode.addChild(game.workstationEventNode)
        addInteractionListeners()
    }

    private fun addInteractionListeners() {
        eventNode.listen<PlayerBlockInteractEvent> {
            filters += GamePlayerFilter(game)
            filters += { this.hand == Player.Hand.MAIN }
            handler {
                val workstation = game.workstationRegistry.find(this.blockPosition) ?: return@handler
                workstation.handler.useStation(this.player)
            }
        }
    }
}