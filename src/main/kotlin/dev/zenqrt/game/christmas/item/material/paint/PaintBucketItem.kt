package dev.zenqrt.game.christmas.item.material.paint

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.utils.PlayerHead
import dev.zenqrt.game.christmas.utils.singleStackingRule
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import world.cepi.kstom.adventure.noItalic

class PaintBucketItem : Item {
    override val id = "paint_bucket"
    override val model = PlayerHead.builder("http://textures.minecraft.net/texture/f72b5cf4fb2e3d9158a4f5ece714970bcc1371ca029e645555927fd156e184")
        .singleStackingRule()
        .displayName(Component.text("Paint", NamedTextColor.YELLOW).noItalic())
        .build()
}