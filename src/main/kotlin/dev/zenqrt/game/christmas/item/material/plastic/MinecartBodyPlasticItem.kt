package dev.zenqrt.game.christmas.item.material.plastic

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.item.material.paint.Paintable
import dev.zenqrt.game.christmas.item.material.plastic.MinecartBody.DISPLAY_NAME
import dev.zenqrt.game.christmas.utils.PlayerHead
import dev.zenqrt.game.christmas.utils.singleStackingRule
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import world.cepi.kstom.adventure.noItalic

class MinecartBodyPlasticItem : Item, Paintable {
    override val id = "plastic_minecart_body"
    override val model = ItemStack.builder(Material.MINECART)
        .displayName(DISPLAY_NAME)
        .build()
    override val paintedItem = PaintedMinecartBodyPlasticItem()
}

class PaintedMinecartBodyPlasticItem : Item {
    override val id = "painted_plastic_minecart_body"
    override val model = PlayerHead.builder("http://textures.minecraft.net/texture/c42070acc814bc946e59879ec7da45de984d3ee9a159393defb59853abe3b6")
        .singleStackingRule()
        .displayName(DISPLAY_NAME)
        .build()
}

private object MinecartBody {
    val DISPLAY_NAME = Component.text("Minecart Body", NamedTextColor.GREEN).noItalic()
}