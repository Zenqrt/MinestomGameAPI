package dev.zenqrt.game.christmas.utils

import dev.zenqrt.game.christmas.item.Item
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import net.minestom.server.MinecraftServer
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class ItemStackExtensionsTests : ShouldSpec({
    beforeSpec { MinecraftServer.init() }

    context("ItemStack") {
        val item = TestItem()

        should("match with item") {
            val itemStack = item.createItemStack()
            itemStack.isItem(item) shouldBe true
        }

        should("not match with item") {
            val itemStack = ItemStack.AIR
            itemStack.isItem(item) shouldBe false
        }
    }
})

class TestItem : Item {
    override val id = "test"
    override val model = ItemStack.of(Material.ACACIA_BOAT)
}