package dev.zenqrt.game.christmas.item

import dev.zenqrt.game.christmas.item.material.metal.MetalItem
import dev.zenqrt.game.christmas.item.material.paint.PaintBucketItem
import dev.zenqrt.game.christmas.item.material.plastic.*
import dev.zenqrt.game.christmas.item.material.toy.*
import dev.zenqrt.game.christmas.item.material.wood.PaintedSledWoodItem
import dev.zenqrt.game.christmas.item.material.wrapping.WrappedPresentItem
import dev.zenqrt.game.christmas.item.material.wrapping.WrappingColors
import dev.zenqrt.game.christmas.item.material.wood.SledWoodItem
import dev.zenqrt.game.christmas.item.material.wood.WoodItem
import dev.zenqrt.game.christmas.item.material.wrapping.PresentWrapItem
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

interface Item {
    val id: String
    val model: ItemStack

    fun createItemStack(): ItemStack = model.withTag(ID_TAG, id)

    companion object {
        val ID_TAG = Tag.String("item_id")
    }
}

//open class Item(open val id: String, open val model: ItemStack) {
//    fun createItemStack(): ItemStack = model.withTag(ID_TAG, id)
//
//    companion object {
//        val ID_TAG = Tag.String("item_id")
//    }
//}

object Items {
    val REGISTRY = mutableMapOf<String, Item>()

    val METAL = register(MetalItem())
    val PAINT_BUCKET = register(PaintBucketItem())
    val PAINTED_PLASTIC_MINECART_BODY = register(PaintedMinecartBodyPlasticItem())
    val PAINTED_PLASTIC_TRUCK_BODY = register(PaintedTruckBodyPlasticItem())
    val PAINTED_PLASTIC_WHEEL = register(PaintedWheelPlasticItem())
    val PAINTED_WOOD_SLED = register(PaintedSledWoodItem())
    val PLASTIC = register(PlasticItem())
    val PLASTIC_MINECART_BODY = register(MinecartBodyPlasticItem())
    val PLASTIC_TRUCK_BODY = register(TruckBodyPlasticItem())
    val PLASTIC_WHEEL = register(WheelPlasticItem())
    val PRESENT_WRAP = register(PresentWrapItem())
    val TOY_MINECART = register(MinecartToyItem())
    val TOY_TRUCK = register(TruckToyItem())
    val WOOD = register(WoodItem())
    val WOOD_SLED = register(SledWoodItem())
    val WRAPPED_PRESENT = register(WrappedPresentItem("None", WrappingColors.RED))

    private fun <T : Item> register(item: T): T = item.also { REGISTRY[item.id] = item }
}