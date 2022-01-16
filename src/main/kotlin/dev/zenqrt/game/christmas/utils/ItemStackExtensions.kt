package dev.zenqrt.game.christmas.utils

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.SingleStackingRule
import dev.zenqrt.game.christmas.item.toy.material.Paintable
import dev.zenqrt.game.christmas.item.toy.material.wrapping.WrappableItem
import net.minestom.server.item.ItemStack
import net.minestom.server.item.ItemStackBuilder
import net.minestom.server.item.Material

val ItemStack.painted: Boolean
    get() = if(this.hasTag(Paintable.PAINTED_TAG)) this.getTag(Paintable.PAINTED_TAG) == 1 else false

val ItemStack.wrappable: Boolean
    get() = this.hasTag(WrappableItem.WRAPPABLE_TAG)

fun ItemStack.isItem(item: Item): Boolean = this.getTag(Item.ID_TAG) == item.id

fun ItemStackBuilder.singleStackingRule(): ItemStackBuilder = this.stackingRule(SingleStackingRule())