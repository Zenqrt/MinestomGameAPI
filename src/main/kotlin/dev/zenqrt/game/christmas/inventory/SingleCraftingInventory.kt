package dev.zenqrt.game.christmas.inventory

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.recipe.SingleRecipe
import dev.zenqrt.game.christmas.utils.fill
import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.inventory.InventoryCloseEvent
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.event.trait.InventoryEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.event.listenOnly

class SingleCraftingInventory(title: Component, private val recipes: List<SingleRecipe>) : Inventory(InventoryType.CHEST_5_ROW, title) {
    constructor(title: String, recipes: List<SingleRecipe>) : this(Component.text(title), recipes)

    init {
        val borderItem = ItemStack.builder(Material.GRAY_STAINED_GLASS_PANE)
            .displayName(Component.empty())
            .build()

        this.fill(borderItem)

        clearInput()
        clearOutput()
        clearRecipeShowcase()
    }

    private fun setInput(itemStack: ItemStack) {
        this.setItemStack(INPUT_SLOT, itemStack)
    }

    private fun getInput(): ItemStack = this.getItemStack(INPUT_SLOT)

    private fun clearInput() {
        setInput(ItemStack.AIR)
    }

    private fun returnInputItem(player: Player) {
        val input = getInput()
        if(input.isAir) return

        player.inventory.addItemStack(input)
    }

    private fun setOutput(itemStack: ItemStack) {
        this.setItemStack(OUTPUT_SLOT, itemStack)
    }

    private fun getOutput(): ItemStack = this.getItemStack(OUTPUT_SLOT)

    private fun clearOutput() {
        setOutput(ItemStack.AIR)
    }

    private fun returnOutputItem(player: Player) {
        val output = getOutput()
        if(output.isAir) return

        player.inventory.addItemStack(output)
    }

    private fun displayRecipes(inventory: Inventory, input: ItemStack) {
        if(input.isAir) {
            clearRecipeShowcase()
        } else {
            var index = 0
            iterateRecipeShowcase {
                if(index >= recipes.size) {
                    false
                } else {
                    inventory.setItemStack(it, recipes[index].result)
                    index++
                    true
                }
            }
        }
    }

    private fun clearRecipeShowcase() {
        iterateRecipeShowcase {
            this.setItemStack(it, ItemStack.AIR)
            true
        }
    }

    private fun iterateRecipeShowcase(handler: (Int) -> Boolean) {
        for(x in 3..7) {
            for(y in 1..3) {
                if(!handler(((y * 9) + x))) return
            }
        }
    }

    fun createEventNode(): EventNode<InventoryEvent> = EventNode.type("single_crafting", EventFilter.INVENTORY).also {
        it.listenOnly<InventoryPreClickEvent> {
            this.inventory?.let { inventory ->
                when(this.slot) {
                    INPUT_SLOT -> { displayRecipes(inventory, this.cursorItem) }
                    OUTPUT_SLOT -> {}
                    else -> {
                        this.isCancelled = true
                        if(!this.clickedItem.hasTag(Item.ID_TAG)) return@listenOnly

                        inventory.setItemStack(OUTPUT_SLOT, this.clickedItem)
                    }
                }
            }
        }

        it.listenOnly<InventoryCloseEvent> {
            this.inventory?.let { inventory ->
                val craftingInventory = inventory as SingleCraftingInventory
                craftingInventory.returnInputItem(this.player)
                craftingInventory.returnOutputItem(this.player)
            }
        }
    }

    companion object {
        const val INPUT_SLOT = 10
        const val OUTPUT_SLOT = 28
    }
}