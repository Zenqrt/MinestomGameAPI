package dev.zenqrt.game.christmas.recipe

import dev.zenqrt.game.christmas.item.Items
import net.minestom.server.item.ItemStack

open class PlasticRecipe(override val id: String, override val result: ItemStack) : SingleRecipe {
    override val ingredient = Items.PLASTIC.createItemStack()
}

class MinecartBodyPlasticRecipe : PlasticRecipe("plastic_minecart_body", Items.PLASTIC_MINECART_BODY.createItemStack())
class TruckBodyPlasticRecipe : PlasticRecipe("plastic_truck_body", Items.PLASTIC_TRUCK_BODY.createItemStack())
class WheelPlasticRecipe : PlasticRecipe("plastic_wheel", Items.PLASTIC_WHEEL.createItemStack())