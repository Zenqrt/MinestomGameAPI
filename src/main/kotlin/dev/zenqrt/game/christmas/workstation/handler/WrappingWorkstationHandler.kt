package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.item.material.wrapping.WrappedPresentItem
import dev.zenqrt.game.christmas.item.material.wrapping.WrappingColors
import dev.zenqrt.game.christmas.utils.wrappable
import net.kyori.adventure.sound.Sound
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.item.ItemStack
import net.minestom.server.sound.SoundEvent
import world.cepi.kstom.adventure.plainText

class WrappingWorkstationHandler(eventNode: EventNode<Event>) : GrindstoneInventoryWorkstationHandler("Wrapping Station", Items.PRESENT_WRAP.createItemStack(), eventNode) {
    override val useSound = Sound.sound(SoundEvent.ITEM_BUNDLE_DROP_CONTENTS, Sound.Source.MASTER, 10F, 1.25F)
    override fun getResult(itemStack: ItemStack): ItemStack? = if(itemStack.wrappable) WrappedPresentItem(itemStack.displayName!!.plainText(), WrappingColors.GREEN).createItemStack() else null
}