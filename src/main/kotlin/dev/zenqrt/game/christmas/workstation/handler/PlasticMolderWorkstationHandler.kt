package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.inventory.SingleCraftingInventory
import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.recipe.CustomRecipes
import dev.zenqrt.game.christmas.recipe.PlasticRecipe
import dev.zenqrt.game.christmas.utils.fill
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.inventory.InventoryCloseEvent
import net.minestom.server.event.inventory.InventoryItemChangeEvent
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.event.trait.InventoryEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.event.listenOnly

class PlasticMolderWorkstationHandler(private val eventNode: EventNode<Event>) : WorkstationHandler {
    private val plasticRecipes = listOf<PlasticRecipe>(
        CustomRecipes.PLASTIC_WHEEL
    )
    private val inventoryUI: SingleCraftingInventory
        get() = SingleCraftingInventory("Plastic Molding Station", plasticRecipes)

    override fun useStation(player: Player) {
        val inventory = inventoryUI
        mapEventNode(inventory)
        player.openInventory(inventory)
    }

    private fun mapEventNode(inventory: SingleCraftingInventory) {
        eventNode.map(inventory.createEventNode(), inventory)
    }
}