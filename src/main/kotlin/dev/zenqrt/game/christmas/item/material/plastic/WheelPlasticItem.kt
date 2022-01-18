package dev.zenqrt.game.christmas.item.material.plastic

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.item.material.paint.Paintable
import dev.zenqrt.game.christmas.item.material.plastic.Wheel.DISPLAY_NAME
import dev.zenqrt.game.christmas.utils.PlayerHead
import dev.zenqrt.game.christmas.utils.singleStackingRule
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import world.cepi.kstom.adventure.noItalic

class WheelPlasticItem : Item, Paintable {
    override val id = "plastic_wheel"
    override val model = PlayerHead.builder("http://textures.minecraft.net/texture/ddfe5a963869415340d2cec0f82d08df73dcb168428487b514aa8d4ec19fe2c")
        .singleStackingRule()
        .displayName(DISPLAY_NAME)
        .build()
    override val paintedItem = Items.PAINTED_PLASTIC_WHEEL
}

class PaintedWheelPlasticItem : Item {
    override val id = "painted_plastic_wheel"
    override val model = PlayerHead.builder("http://textures.minecraft.net/texture/ddfe5a963869415340d2cec0f82d08df73dcb168428487b514aa8d4ec19fe2c")
        .singleStackingRule()
        .displayName(DISPLAY_NAME)
        .build()
}

private object Wheel {
    val DISPLAY_NAME = Component.text("Plastic Wheel", NamedTextColor.GREEN).noItalic()
}

