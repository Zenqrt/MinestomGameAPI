package dev.zenqrt.game.christmas.workstation.handler

import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.item.toy.material.wrapping.WrappedPresentItem
import dev.zenqrt.game.christmas.item.toy.material.wrapping.WrappingColors
import dev.zenqrt.game.christmas.utils.wrappable
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.item.ItemStack
import world.cepi.kstom.adventure.plainText

class WrappingWorkstationHandler(eventNode: EventNode<Event>) : GrindstoneInventoryWorkstationHandler("Wrapping Station", Items.PRESENT_WRAP.createItemStack(), eventNode) {
    override fun getResult(itemStack: ItemStack): ItemStack? = if(itemStack.wrappable) WrappedPresentItem(itemStack.displayName!!.plainText(), WrappingColors.GREEN).createItemStack() else null
}