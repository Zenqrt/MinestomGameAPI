package dev.zenqrt.game.christmas.item.toy.material.wrapping

import dev.zenqrt.game.christmas.item.Item
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag

abstract class WrappableItem : Item {
    override fun createItemStack(): ItemStack = super.createItemStack().withTag(WRAPPABLE_TAG, 1)

    companion object {
        val WRAPPABLE_TAG = Tag.Integer("wrappable")
    }
}