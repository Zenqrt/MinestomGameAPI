package dev.zenqrt.game.christmas.item

import dev.zenqrt.game.christmas.item.toy.material.wrapping.WrappedPresentItem
import dev.zenqrt.game.christmas.item.toy.material.wrapping.WrappingColors
import dev.zenqrt.game.christmas.item.toy.material.plastic.WheelPlasticItem
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
    val PLASTIC_WHEEL = WheelPlasticItem()
    val WRAPPED_PRESENT = WrappedPresentItem("None", WrappingColors.RED)
}