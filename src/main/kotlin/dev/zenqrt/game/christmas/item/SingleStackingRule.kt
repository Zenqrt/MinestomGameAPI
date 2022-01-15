package dev.zenqrt.game.christmas.item

import net.minestom.server.item.ItemStack
import net.minestom.server.item.StackingRule

class SingleStackingRule : StackingRule {
    override fun canBeStacked(item1: ItemStack, item2: ItemStack): Boolean = item1.amount + item2.amount <= 1
    override fun canApply(item: ItemStack, amount: Int): Boolean = amount <= 1
    override fun apply(item: ItemStack, newAmount: Int): ItemStack = item.withAmount(newAmount)
    override fun getAmount(itemStack: ItemStack): Int = itemStack.amount
    override fun getMaxSize(itemStack: ItemStack): Int = 1
}