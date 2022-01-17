package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.inventory.DoubleCraftingInventory
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode

class CraftingWorkstationHandler(private val eventNode: EventNode<Event>) : WorkstationHandler {
    private val inventoryUI: DoubleCraftingInventory
        get() = DoubleCraftingInventory("Crafting Workstation", listOf())

    override fun useStation(player: Player) {
        val inventory = inventoryUI
        eventNode.map(inventory.createEventNode(), inventory)
        player.openInventory(inventory)
    }
}