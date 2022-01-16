package dev.zenqrt.game.christmas.recipe

import dev.zenqrt.game.christmas.item.Items
import net.minestom.server.item.ItemStack

open class PlasticRecipe(override val id: String, override val ingredient: ItemStack,
                         override val result: ItemStack) : SingleRecipe

class WheelPlasticRecipe : PlasticRecipe("plastic_wheel", Items.PLASTIC.createItemStack(), Items.PLASTIC_WHEEL.createItemStack())