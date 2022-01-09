package dev.zenqrt.game.christmas.utils

import dev.zenqrt.game.api.player.AccessibleFakePlayer
import io.kotest.assertions.fail
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import net.minestom.server.MinecraftServer
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import java.util.*

class InventoryExtensionsTests : ShouldSpec({

    beforeSpec { MinecraftServer.init() }

    context("Inventory") {
        should("have an empty slot") {
            val player = AccessibleFakePlayer(UUID.randomUUID(), "test_player")
            player.inventory.hasEmptySlot() shouldBe true
        }

        should("not have an empty slot") {
            val player = AccessibleFakePlayer(UUID.randomUUID(), "test_player")
            for(i in 0 until player.inventory.size) {
                player.inventory.setItemStack(i, ItemStack.of(Material.ACACIA_BOAT))
            }
            player.inventory.hasEmptySlot() shouldBe false
        }
    }

})