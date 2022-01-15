package dev.zenqrt.game.christmas.item.toy.material

import dev.zenqrt.game.christmas.item.Item
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

interface Paintable : Item {
    val paintedModel: ItemStack

    fun createPaintedItemStack(): ItemStack = createItemStack().withTag(PAINTED_TAG, 1)

    companion object {
        val PAINTED_TAG = Tag.Integer("painted")
    }
}