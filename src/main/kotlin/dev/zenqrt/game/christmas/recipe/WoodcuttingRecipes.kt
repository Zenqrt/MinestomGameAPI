package dev.zenqrt.game.christmas.recipe

import dev.zenqrt.game.christmas.item.Items
import net.minestom.server.item.ItemStack

open class WoodcuttingRecipe(override val id: String, override val result: ItemStack) : SingleRecipe {
    override val ingredient = Items.WOOD.createItemStack()
}

class SledWoodRecipe : WoodcuttingRecipe("wood_sled", Items.WOOD_SLED.createItemStack())