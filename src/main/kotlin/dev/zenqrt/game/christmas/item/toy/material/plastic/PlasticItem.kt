package dev.zenqrt.game.christmas.item.toy.material.plastic

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.toy.material.paint.Paintable
import dev.zenqrt.game.christmas.utils.PlayerHead
import dev.zenqrt.game.christmas.utils.singleStackingRule
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.adventure.noItalic

class PlasticItem : Item {
    override val id = "plastic"
    override val model = ItemStack.builder(Material.GREEN_CONCRETE)
        .singleStackingRule()
        .displayName(
            Component.text("Plastic", NamedTextColor.GREEN).noItalic()
        ).build()
}