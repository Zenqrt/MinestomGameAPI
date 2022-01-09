package dev.zenqrt.game.christmas.phase.trait

import dev.zenqrt.game.api.event.filter.GamePlayerFilter
import dev.zenqrt.game.api.phase.trait.PhaseTrait
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.utils.teleport
import dev.zenqrt.game.christmas.workstation.Workstation
import dev.zenqrt.game.christmas.workstation.WorkstationInteractionBox
import dev.zenqrt.game.christmas.workstation.handler.PaintingWorkstationHandler
import dev.zenqrt.game.christmas.workstation.handler.SantaSleighWorkstationHandler
import dev.zenqrt.game.christmas.workstation.handler.WorkstationHandler
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Entity
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.PlayerEntityInteractEvent
import net.minestom.server.instance.Instance
import java.io.File

class WorkstationPhaseTrait(private val eventNode: EventNode<Event>, private val game: ChristmasGame) : PhaseTrait {
    private val interactionEntities = mutableMapOf<Entity, Workstation>()

    override fun handleTrait() {
        registerWorkstations()
        addInteractionListeners()
    }

    private fun registerWorkstations() {
        registerWorkstation(fromJson("paint"), PaintingWorkstationHandler())
        registerWorkstation(fromJson("santa_sleigh"), SantaSleighWorkstationHandler(game))
    }

    private fun registerWorkstation(interactionBoxes: List<WorkstationInteractionBox>, workstationHandler: WorkstationHandler) {
        interactionBoxes.forEach { registerWorkstation(Workstation(it, workstationHandler)) }
    }

    private fun registerWorkstation(workstation: Workstation) {
        val interactionBox = workstation.interactionBox
        val interactionEntity = interactionBox.createInteractionEntity()
        interactionEntity.teleport(game.instance, Pos(interactionBox.minX, interactionBox.minY, interactionBox.minZ))
        interactionEntities[interactionEntity] = workstation
    }

    private fun fromJson(name: String): List<WorkstationInteractionBox> = Json.decodeFromString(File("./src/main/resources/workstations/$name.json").readText())

    private fun addInteractionListeners() {
        eventNode.addListener(EventListener.builder(PlayerEntityInteractEvent::class.java)
            .filter(GamePlayerFilter(game))
            .handler { event ->
                val entity = interactionEntities[event.target]
                entity?.handler?.useStation(event.player)
            }.build())
    }
}