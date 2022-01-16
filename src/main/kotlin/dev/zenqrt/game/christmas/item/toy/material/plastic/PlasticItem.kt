package dev.zenqrt.game.christmas.item.toy.material.plastic

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.toy.material.Paintable
import dev.zenqrt.game.christmas.utils.PlayerHead
import dev.zenqrt.game.christmas.utils.singleStackingRule
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class PlasticItem : Item {
    override val id = "plastic"
    override val model = ItemStack.builder(Material.GREEN_CONCRETE)
        .singleStackingRule()
        .displayName(
            Component.text("Plastic", NamedTextColor.GREEN)
                .decoration(TextDecoration.ITALIC, false)
        ).build()
}

class WheelPlasticItem : Item, Paintable {
    override val id = "plastic_wheel"
    override val model = PlayerHead.builder("http://textures.minecraft.net/texture/ddfe5a963869415340d2cec0f82d08df73dcb168428487b514aa8d4ec19fe2c")
        .singleStackingRule()
        .displayName(Component.text("Plastic Wheel", NamedTextColor.GREEN)
            .decoration(TextDecoration.ITALIC, false))
        .build()
    override val paintedModel = PlayerHead.builder("http://textures.minecraft.net/texture/ddfe5a963869415340d2cec0f82d08df73dcb168428487b514aa8d4ec19fe2c")
        .singleStackingRule()
        .displayName(Component.text("Plastic Wheel", NamedTextColor.GREEN)
            .decoration(TextDecoration.ITALIC, false))
        .build()

}

class TruckBodyPlasticItem : Item, Paintable {
    override val id = "plastic_truck_body"
    override val model = ItemStack.AIR
    override val paintedModel = PlayerHead.builder("http://textures.minecraft.net/texture/eeadd00a8d195c235551bf58bdc4c0674d4b3ca4790812e1858a048d60d07d08")
        .singleStackingRule()
        .displayName(model.displayName)
        .build()
}

class MinecartBodyPlasticItem : Item, Paintable {
    override val id = "plastic_minecart_body"
    override val model = ItemStack.AIR
    override val paintedModel = PlayerHead.builder("http://textures.minecraft.net/texture/c42070acc814bc946e59879ec7da45de984d3ee9a159393defb59853abe3b6")
        .singleStackingRule()
        .displayName(model.displayName)
        .build()
}

class BoatBodyPlasticItem : Item {
    override val id = "plastic_boat_body"
    override val model = ItemStack.AIR
}