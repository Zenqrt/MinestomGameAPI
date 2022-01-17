package dev.zenqrt.game.christmas.recipe

import dev.zenqrt.game.christmas.item.Items
import net.minestom.server.item.ItemStack

open class PlasticRecipe(override val id: String, override val result: ItemStack) : SingleRecipe {
    override val ingredient = Items.PLASTIC.createItemStack()
}

class WheelPlasticRecipe : PlasticRecipe("plastic_wheel", Items.PLASTIC_WHEEL.createItemStack())