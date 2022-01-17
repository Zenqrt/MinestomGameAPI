package dev.zenqrt.game.christmas.item.toy.material.plastic

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.item.toy.material.paint.Paintable
import dev.zenqrt.game.christmas.utils.PlayerHead
import dev.zenqrt.game.christmas.utils.singleStackingRule
import net.minestom.server.item.ItemStack

class MinecartBodyPlasticItem : Item, Paintable {
    override val id = "plastic_minecart_body"
    override val model = ItemStack.AIR
    override val paintedItem = Items.PAINTED_PLASTIC_MINECART_BODY
}

class PaintedMinecartBodyPlasticItem : Item {
    override val id = "painted_plastic_minecart_body"
    override val model = PlayerHead.builder("http://textures.minecraft.net/texture/c42070acc814bc946e59879ec7da45de984d3ee9a159393defb59853abe3b6")
        .singleStackingRule()
        .displayName(Items.PLASTIC_MINECART_BODY.model.displayName)
        .build()
}