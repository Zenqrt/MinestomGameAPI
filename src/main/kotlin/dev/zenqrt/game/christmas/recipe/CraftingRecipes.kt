package dev.zenqrt.game.christmas.recipe

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.Items

open class CraftingRecipe(override val id: String, firstIngredient: Item, secondIngredient: Item, result: Item) : DoubleRecipe {
    override val firstIngredient = firstIngredient.createItemStack()
    override val secondIngredient = secondIngredient.createItemStack()
    override val result = result.createItemStack()
}


class MinecartToyRecipe : CraftingRecipe("toy_minecart", Items.PAINTED_PLASTIC_MINECART_BODY, Items.PAINTED_PLASTIC_WHEEL, Items.TOY_MINECART)
class TruckToyRecipe : CraftingRecipe("toy_truck", Items.PAINTED_PLASTIC_TRUCK_BODY, Items.PAINTED_PLASTIC_WHEEL, Items.TOY_TRUCK)