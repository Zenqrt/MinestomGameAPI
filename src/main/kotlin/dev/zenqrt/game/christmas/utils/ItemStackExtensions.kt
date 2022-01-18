package dev.zenqrt.game.christmas.utils

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.SingleStackingRule
import dev.zenqrt.game.christmas.item.material.wrapping.WrappableItem
import net.minestom.server.item.ItemStack
import net.minestom.server.item.ItemStackBuilder

val ItemStack.wrappable: Boolean
    get() = this.hasTag(WrappableItem.WRAPPABLE_TAG)

fun ItemStack.isItem(item: Item): Boolean = this.getTag(Item.ID_TAG) == item.id

fun ItemStackBuilder.singleStackingRule(): ItemStackBuilder = this.stackingRule(SingleStackingRule())