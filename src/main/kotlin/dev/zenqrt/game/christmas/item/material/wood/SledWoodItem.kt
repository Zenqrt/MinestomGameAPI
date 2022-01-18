package dev.zenqrt.game.christmas.item.material.wood

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.item.material.paint.Paintable
import dev.zenqrt.game.christmas.item.material.wood.SledWood.DISPLAY_NAME
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.adventure.noItalic

class SledWoodItem : Item, Paintable {
    override val id = "wood_sled"
    override val model = ItemStack.builder(Material.OAK_BOAT)
        .displayName(DISPLAY_NAME).build()
    override val paintedItem = PaintedSledWoodItem()

}

class PaintedSledWoodItem : Item {
    override val id = "painted_wood_sled"
    override val model = ItemStack.builder(Material.ACACIA_BOAT)
        .displayName(DISPLAY_NAME)
        .build()
}

private object SledWood {
    val DISPLAY_NAME = Component.text("Sled", NamedTextColor.GOLD).noItalic()
}