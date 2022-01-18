package dev.zenqrt.game.christmas.item.material.wrapping

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.utils.PlayerHead
import dev.zenqrt.game.christmas.utils.singleStackingRule
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import world.cepi.kstom.adventure.noItalic

class PresentWrapItem : Item {
    override val id = "present_wrap"
    override val model = PlayerHead.builder("http://textures.minecraft.net/texture/a1300dcd5790c94738644898ce6e75e8c9d990b3f1e7a5e3d1223906de2bb553")
        .singleStackingRule()
        .displayName(Component.text("Present Wrap", NamedTextColor.RED).noItalic())
        .build()
}