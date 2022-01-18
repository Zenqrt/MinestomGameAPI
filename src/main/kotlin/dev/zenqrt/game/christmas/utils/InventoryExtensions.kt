package dev.zenqrt.game.christmas.utils

import net.minestom.server.inventory.AbstractInventory
import net.minestom.server.inventory.PlayerInventory
import net.minestom.server.item.ItemStack

fun AbstractInventory.hasEmptySlot(): Boolean = this.itemStacks.any { it.isAir }
fun PlayerInventory.hasEmptySlot(): Boolean {
    for(i in 0 until 36) if(this.getItemStack(i).isAir) return true
    return false
}
fun AbstractInventory.fill(itemStack: ItemStack) {
    for(i in 0 until this.size) {
        if(this.getItemStack(i).isAir) {
            this.setItemStack(i, itemStack)
        }
    }
}
