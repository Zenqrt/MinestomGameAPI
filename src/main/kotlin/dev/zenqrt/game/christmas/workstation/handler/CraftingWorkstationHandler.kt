package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.inventory.DoubleCraftingInventory
import dev.zenqrt.game.christmas.recipe.MinecartToyRecipe
import dev.zenqrt.game.christmas.recipe.TruckToyRecipe
import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.sound.SoundEvent

class CraftingWorkstationHandler(private val eventNode: EventNode<Event>) : WorkstationHandler {
    private val recipes = listOf(
        MinecartToyRecipe(),
        TruckToyRecipe()
    )

    private val inventoryUI: DoubleCraftingInventory
        get() = DoubleCraftingInventory("Crafting Workstation", Sound.sound(SoundEvent.ENTITY_VILLAGER_WORK_FLETCHER, Sound.Source.MASTER, 10F, 1F), recipes)

    override fun useStation(player: Player) {
        val inventory = inventoryUI
        eventNode.map(inventory.createEventNode(), inventory)
        player.openInventory(inventory)
    }
}