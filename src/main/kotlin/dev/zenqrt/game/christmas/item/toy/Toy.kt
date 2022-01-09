package dev.zenqrt.game.christmas.item.toy

import dev.zenqrt.game.christmas.item.Item
import net.minestom.server.item.ItemStack

object Toys {
//    val TEDDY_BEAR = Toy("teddy_bear", PlayerHeadBuilder.create("http://textures.minecraft.net/texture/d5f5bf8510ffcd3a5e9d7825b64333a121d561fe2cdd767c7e18b8cc521b6"), ToyBlueprint())
}

data class Toy(override val id: String, override val model: ItemStack, val blueprint: ToyBlueprint) : Item

data class ToyBlueprint(val items: List<ItemStack>, val buildTime: Int)