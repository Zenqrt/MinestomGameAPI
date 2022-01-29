package dev.zenqrt.game.christmas.item.material.toy

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.item.Items
import dev.zenqrt.game.christmas.item.material.paint.Paintable
import dev.zenqrt.game.christmas.item.material.toy.TruckBody.DISPLAY_NAME
import dev.zenqrt.game.christmas.item.material.wrapping.WrappableItem
import dev.zenqrt.game.christmas.utils.PlayerHead
import dev.zenqrt.game.christmas.utils.singleStackingRule
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import world.cepi.kstom.adventure.noItalic

class TruckToyItem : WrappableItem() {
    override val id = "toy_truck"
    override val model = PlayerHead.builder("http://textures.minecraft.net/texture/eeadd00a8d195c235551bf58bdc4c0674d4b3ca4790812e1858a048d60d07d08")
        .singleStackingRule()
        .displayName(Component.text("Toy Truck", NamedTextColor.AQUA).noItalic())
        .build()
}

class TruckBodyPlasticItem : Item, Paintable {
    override val id = "plastic_truck_body"
    override val model = PlayerHead.builder("http://textures.minecraft.net/texture/eeadd00a8d195c235551bf58bdc4c0674d4b3ca4790812e1858a048d60d07d08")
        .singleStackingRule()
        .displayName(DISPLAY_NAME)
        .build()
    override val paintedItem = Items.PAINTED_PLASTIC_TRUCK_BODY
}

class PaintedTruckBodyPlasticItem : Item {
    override val id = "painted_plastic_truck_body"
    override val model = PlayerHead.builder("http://textures.minecraft.net/texture/eeadd00a8d195c235551bf58bdc4c0674d4b3ca4790812e1858a048d60d07d08")
        .singleStackingRule()
        .displayName(Paintable.getPaintedDisplayName(DISPLAY_NAME))
        .build()
}

private object TruckBody {
    val DISPLAY_NAME = Component.text("Truck Body", NamedTextColor.GREEN)
}