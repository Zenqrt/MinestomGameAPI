package dev.zenqrt.game.christmas.item.toy.material.wood

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.item.toy.material.paint.Paintable
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.adventure.noItalic

class SledWoodItem : Item, Paintable {
    override val id = "wood_sled"
    override val model = ItemStack.builder(Material.OAK_BOAT)
        .displayName(
            Component.text("Sled", NamedTextColor.GOLD).noItalic()
        ).build()
    override val paintedItem = Items.PAINTED_WOOD_SLED

}

class PaintedSledWoodItem : Item {
    override val id = "painted_wood_sled"
    override val model = ItemStack.builder(Material.ACACIA_BOAT)
        .displayName(Items.WOOD_SLED.model.displayName)
        .build()
}