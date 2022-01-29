package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.item.material.paint.Paintable
import net.kyori.adventure.sound.Sound
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.item.ItemStack
import net.minestom.server.sound.SoundEvent

class PaintingWorkstationHandler(eventNode: EventNode<Event>) : GrindstoneInventoryWorkstationHandler("Painting Station", Items.PAINT_BUCKET.createItemStack(), eventNode) {
    override val useSound = Sound.sound(SoundEvent.ENTITY_GENERIC_SPLASH, Sound.Source.MASTER, 10F, 1.5F)
    override fun getResult(itemStack: ItemStack): ItemStack? = itemStack.getTag(Paintable.PAINTED_ID_TAG)?.let { Items.REGISTRY[it]?.createItemStack() }
}