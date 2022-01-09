package dev.zenqrt.game.christmas.utils

import net.minestom.server.inventory.AbstractInventory

fun AbstractInventory.hasEmptySlot(): Boolean = this.itemStacks.any { it.isAir }
