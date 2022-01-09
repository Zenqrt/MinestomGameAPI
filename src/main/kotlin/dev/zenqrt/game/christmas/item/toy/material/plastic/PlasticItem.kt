package dev.zenqrt.game.christmas.item.toy.material.plastic

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.utils.PlayerHead
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class PlasticItem : Item {
    override val id = "plastic"
    override val model = ItemStack.builder(Material.GREEN_CONCRETE)
        .displayName(
            Component.text("Plastic", NamedTextColor.GREEN)
                .decoration(TextDecoration.ITALIC, false)
        ).build()
}

class WheelPlasticItem : Item {
    override val id = "plastic_wheel"
    override val model = PlayerHead.create("http://textures.minecraft.net/texture/ddfe5a963869415340d2cec0f82d08df73dcb168428487b514aa8d4ec19fe2c")
        .withDisplayName(Component.text("Plastic Wheel", NamedTextColor.GREEN)
            .decoration(TextDecoration.ITALIC, false))
}