package dev.zenqrt.game.christmas.inventory

import dev.zenqrt.game.christmas.recipe.DoubleRecipe
import net.kyori.adventure.text.Component
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.inventory.InventoryPreClickEvent
import net.minestom.server.event.trait.InventoryEvent
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.util.get
import world.cepi.kstom.util.set

class DoubleCraftingInventory(title: Component, private val recipes: List<DoubleRecipe>) : Inventory(InventoryType.GRINDSTONE, title) {
    constructor(title: String, recipes: List<DoubleRecipe>) : this(Component.text(title), recipes)

    fun createEventNode(): EventNode<InventoryEvent> = EventNode.type("double_crafting", EventFilter.INVENTORY).also {
        it.listenOnly<InventoryPreClickEvent> {
            inventory?.let { inventory ->
                val (firstIngredient, secondIngredient) = inventory[IntRange(0,1)]
                val recipe = findRecipe(firstIngredient, secondIngredient) ?: return@listenOnly
                inventory[2] = recipe.result
            }
        }
    }

    private fun findRecipe(firstIngredient: ItemStack, secondIngredient: ItemStack): DoubleRecipe? = recipes.find { recipe ->
        val (foundFirst, foundSecond) = validateIngredient(recipe, firstIngredient, foundFirst = false, foundSecond = false)
        if(!foundFirst && !foundSecond) return@find false

        val (foundFirst2, foundSecond2) = validateIngredient(recipe, secondIngredient, foundFirst, foundSecond)
        if(!foundFirst2 || !foundSecond2) return@find false

        true
    }

    private fun validateIngredient(recipe: DoubleRecipe, ingredient: ItemStack, foundFirst: Boolean, foundSecond: Boolean): Pair<Boolean, Boolean> {
        if(!foundFirst) {
            val condition = ingredient == recipe.firstIngredient

            if(condition) {
                return Pair(true, foundSecond)
            }
        }

        if(!foundSecond) {
            val condition = ingredient == recipe.secondIngredient
            return Pair(foundFirst, condition)
        }

        return Pair(false, false)
    }
}