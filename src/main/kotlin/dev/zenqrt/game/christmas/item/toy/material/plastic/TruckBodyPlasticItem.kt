package dev.zenqrt.game.christmas.item.toy.material.plastic

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.item.toy.material.paint.Paintable
import dev.zenqrt.game.christmas.utils.PlayerHead
import dev.zenqrt.game.christmas.utils.singleStackingRule
import net.minestom.server.item.ItemStack

class TruckBodyPlasticItem : Item, Paintable {
    override val id = "plastic_truck_body"
    override val model = ItemStack.AIR
    override val paintedItem = Items.PAINTED_PLASTIC_TRUCK_BODY
}

class PaintedTruckBodyPlasticItem : Item {
    override val id = "painted_plastic_truck_body"
    override val model = PlayerHead.builder("http://textures.minecraft.net/texture/eeadd00a8d195c235551bf58bdc4c0674d4b3ca4790812e1858a048d60d07d08")
        .singleStackingRule()
        .displayName(Items.PLASTIC_TRUCK_BODY.model.displayName)
        .build()

}