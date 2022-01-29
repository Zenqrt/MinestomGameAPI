package dev.zenqrt.game.christmas.item.material.paint

import dev.zenqrt.game.christmas.item.Item
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag
import org.jglrxavpok.hephaistos.nbt.NBTCompound

interface Paintable : Item {
    val paintedItem: Item

    override fun createItemStack(): ItemStack = super.createItemStack().withTag(PAINTED_ID_TAG, paintedItem.id)

    companion object {
        val PAINTED_TAG = Tag.Integer("painted")
        val PAINTED_ID_TAG = Tag.String("painted_id")

        fun getPaintedDisplayName(displayName: Component): Component = Component.text("(Painted) ", NamedTextColor.GRAY).append(displayName)
    }
}