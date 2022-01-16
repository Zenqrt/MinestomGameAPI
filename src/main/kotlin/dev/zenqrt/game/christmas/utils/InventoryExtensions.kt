package dev.zenqrt.game.christmas.utils

import net.minestom.server.inventory.AbstractInventory
import net.minestom.server.item.ItemStack

fun AbstractInventory.hasEmptySlot(): Boolean = this.itemStacks.any { it.isAir }
fun AbstractInventory.fill(itemStack: ItemStack) {
    for(i in 0 until this.size) {
        if(this.getItemStack(i).isAir) {
            this.setItemStack(i, itemStack)
        }
    }
}
