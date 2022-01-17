package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.inventory.SingleCraftingInventory
import dev.zenqrt.game.christmas.recipe.SingleRecipe
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode

abstract class SingleRecipeWorkstationHandler(private val name: String, private val eventNode: EventNode<Event>) : WorkstationHandler {
    abstract val recipes: List<SingleRecipe>
    private val inventoryUI: SingleCraftingInventory
        get() = SingleCraftingInventory(name, recipes)

    override fun useStation(player: Player) {
        val inventory = inventoryUI
        mapEventNode(inventory)
        player.openInventory(inventory)
    }

    private fun mapEventNode(inventory: SingleCraftingInventory) {
        eventNode.map(inventory.createEventNode(), inventory)
    }
}