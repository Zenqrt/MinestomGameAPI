package dev.zenqrt.game.christmas.phase.trait

import dev.zenqrt.game.api.event.filter.GamePlayerFilter
import dev.zenqrt.game.api.phase.trait.PhaseTrait
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.item.toy.material.metal.MetalItem
import dev.zenqrt.game.christmas.item.toy.material.plastic.PlasticItem
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
    private val interactionBlocks = mutableMapOf<Vec, Workstation>()

    override fun handleTrait() {
        registerWorkstations()
        addInteractionListeners()
    }

    private fun registerWorkstations() {
        registerWorkstations(
            "anvil" to AnvilWorkstationHandler(eventNode),
            "collect_battery" to WorkstationHandlerImpl(),
            "collect_metal" to ItemCollectionWorkstationHandler(Items.METAL.createItemStack()),
            "collect_plastic" to ItemCollectionWorkstationHandler(Items.PLASTIC.createItemStack()),
            "collect_stuffing" to WorkstationHandlerImpl(),
            "crafting" to CraftingWorkstationHandler(),
            "paint" to PaintingWorkstationHandler(eventNode),
            "plastic_molding" to PlasticMolderWorkstationHandler(eventNode),
            "santa_sleigh" to SantaSleighWorkstationHandler(game),
            "stuffing" to StuffingWorkstationHandler(),
            "woodcutting" to WoodcuttingWorkstationHandler(eventNode),
            "wrapping" to WrappingWorkstationHandler(eventNode)
        )
    }

    private fun registerWorkstations(vararg pairs: Pair<String, WorkstationHandler>) {
        pairs.forEach { registerWorkstation(fromJson(it.first), it.second) }
    }

    private fun registerWorkstation(interactionBoxes: List<WorkstationInteractionBox>, workstationHandler: WorkstationHandler) {
        interactionBoxes.forEach { registerWorkstation(Workstation(it, workstationHandler)) }
    }

    private fun registerWorkstation(workstation: Workstation) {
        val interactionBox = workstation.interactionBox

        for(x in interactionBox.minX.toInt()..interactionBox.maxX.toInt()) {
            for(y in interactionBox.minY.toInt()..interactionBox.maxY.toInt()) {
                for(z in interactionBox.minZ.toInt()..interactionBox.maxZ.toInt()) {
                    val pos = Vec(x.toDouble(), y.toDouble(), z.toDouble())
                    if(game.instance.getBlock(pos).isAir) continue
                    interactionBlocks[pos] = workstation
                }
            }
        }
    }

    private fun fromJson(name: String): List<WorkstationInteractionBox> = Json.decodeFromString(File("./src/main/resources/workstations/$name.json").readText())

    private fun addInteractionListeners() {
        eventNode.listen<PlayerBlockInteractEvent> {
            filters += GamePlayerFilter(game)
            filters += { this.hand == Player.Hand.MAIN }
            handler {
                val workstation = interactionBlocks[this.blockPosition] ?: return@handler
                workstation.handler.useStation(this.player)
            }
        }
    }
}