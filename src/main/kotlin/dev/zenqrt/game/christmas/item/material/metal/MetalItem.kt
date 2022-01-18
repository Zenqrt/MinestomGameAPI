package dev.zenqrt.game.christmas.item.material.metal

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.SingleStackingRule
import dev.zenqrt.game.christmas.utils.singleStackingRule
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class MetalItem : Item {
    override val id = "metal"
    override val model = ItemStack.builder(Material.IRON_BLOCK)
        .singleStackingRule()
        .displayName(
            Component.text("Metal", NamedTextColor.WHITE)
                .decoration(TextDecoration.ITALIC, false)
        ).build()
}

class GearMetalItem : Item {
    override val id = "metal_gear"
    override val model = ItemStack.AIR
}