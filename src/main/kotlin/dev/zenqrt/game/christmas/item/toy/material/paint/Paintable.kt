package dev.zenqrt.game.christmas.item.toy.material.paint

import dev.zenqrt.game.christmas.item.Item
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag
import org.jglrxavpok.hephaistos.nbt.NBTCompound

interface Paintable : Item {
    val paintedItem: Item

    fun createPaintedItemStack(): ItemStack = paintedItem.createItemStack()
    override fun createItemStack(): ItemStack = super.createItemStack().withTag(PAINTED_NBT_TAG, createPaintedItemStack().toItemNBT())

    companion object {
        val PAINTED_TAG = Tag.Integer("painted")
        val PAINTED_NBT_TAG = Tag.NBT<NBTCompound>("painted_nbt")
    }
}