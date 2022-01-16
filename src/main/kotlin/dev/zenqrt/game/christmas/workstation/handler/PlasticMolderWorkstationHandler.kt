package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.recipe.CustomRecipes
import dev.zenqrt.game.christmas.recipe.PlasticRecipe
import dev.zenqrt.game.christmas.utils.fill
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.inventory.InventoryItemChangeEvent
import net.minestom.server.event.trait.InventoryEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.event.listenOnly

class PlasticMolderWorkstationHandler : WorkstationHandler {
    private val plasticRecipes = listOf<PlasticRecipe>(
        CustomRecipes.PLASTIC_WHEEL
    )
    private val inventoryUI: Inventory
        get() = Inventory(InventoryType.CHEST_5_ROW, "Plastic Molder").also {
            val borderItem = ItemStack.builder(Material.GRAY_STAINED_GLASS_PANE)
                .displayName(Component.empty())
                .build()

            it.fill(borderItem)

            clearInput(it)
            clearOutput(it)
            clearRecipeShowcase(it)
        }

    override fun useStation(player: Player) {
//        declareRecipes(player)
        player.openInventory(inventoryUI)
    }

    private fun createEventNode(): EventNode<InventoryEvent> = EventNode.type("plastic_molder", EventFilter.INVENTORY).also {
        it.listenOnly<InventoryItemChangeEvent> {

        }
    }

    private fun setInput(inventory: Inventory, itemStack: ItemStack) {
        inventory.setItemStack(INPUT_SLOT, itemStack)
    }

    private fun getInput(inventory: Inventory): ItemStack = inventory.getItemStack(INPUT_SLOT)

    private fun returnInputItem(inventory: Inventory, player: Player) {
        val input = getInput(inventory)
        if(input.isAir) return

        player.inventory.addItemStack(input)
    }

    private fun clearInput(inventory: Inventory) {
        setInput(inventory, ItemStack.AIR)
    }

    private fun setOutput(inventory: Inventory, itemStack: ItemStack) {
        inventory.setItemStack(OUTPUT_SLOT, itemStack)
    }

    private fun getOutput(inventory: Inventory): ItemStack = inventory.getItemStack(OUTPUT_SLOT)

    private fun clearOutput(inventory: Inventory) {
        setOutput(inventory, ItemStack.AIR)
    }

    private fun clearRecipeShowcase(inventory: Inventory) {
        // Clear 4x3 area
        for(x in 3..7) {
            for(y in 1..3) {
                inventory.setItemStack(((y * 9) + x), ItemStack.AIR)
            }
        }
    }

    companion object {
        const val INPUT_SLOT = 10
        const val OUTPUT_SLOT = 28
    }
}