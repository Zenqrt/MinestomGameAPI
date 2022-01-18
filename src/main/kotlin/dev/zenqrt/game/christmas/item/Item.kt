package dev.zenqrt.game.christmas.item

import dev.zenqrt.game.christmas.item.material.metal.MetalItem
import dev.zenqrt.game.christmas.item.material.paint.PaintBucketItem
import dev.zenqrt.game.christmas.item.material.plastic.*
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
    val METAL = MetalItem()
    val PAINT_BUCKET = PaintBucketItem()
    val PAINTED_PLASTIC_MINECART_BODY = PaintedMinecartBodyPlasticItem()
    val PAINTED_PLASTIC_TRUCK_BODY = PaintedTruckBodyPlasticItem()
    val PAINTED_PLASTIC_WHEEL = PaintedWheelPlasticItem()
    val PAINTED_WOOD_SLED = PaintedSledWoodItem()
    val PLASTIC = PlasticItem()
    val PLASTIC_MINECART_BODY = MinecartBodyPlasticItem()
    val PLASTIC_TRUCK_BODY = TruckBodyPlasticItem()
    val PLASTIC_WHEEL = WheelPlasticItem()
    val PRESENT_WRAP = PresentWrapItem()
    val WOOD = WoodItem()
    val WOOD_SLED = SledWoodItem()
    val WRAPPED_PRESENT = WrappedPresentItem("None", WrappingColors.RED)
}