package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.item.material.paint.Paintable
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.item.ItemStack

class PaintingWorkstationHandler(eventNode: EventNode<Event>) : GrindstoneInventoryWorkstationHandler("Painting Station", Items.PAINT_BUCKET.createItemStack(), eventNode) {
    override fun getResult(itemStack: ItemStack): ItemStack? = itemStack.getTag(Paintable.PAINTED_NBT_TAG)?.let { ItemStack.fromItemNBT(it) }
}