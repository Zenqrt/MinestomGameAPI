package dev.zenqrt.game.christmas.item.toy.material.wood

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.toy.material.paint.Paintable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class WoodItem : Item {
    override val id = "wood"
    override val model = ItemStack.builder(Material.OAK_WOOD)
        .displayName(
            Component.text("Wood", NamedTextColor.GOLD)
                .decoration(TextDecoration.ITALIC, false)
        ).build()
}