package dev.zenqrt.game.christmas.utils

import net.minestom.server.entity.PlayerSkin
import net.minestom.server.item.ItemStack
import net.minestom.server.item.ItemStackBuilder
import net.minestom.server.item.Material
import net.minestom.server.item.metadata.PlayerHeadMeta
import java.util.*

object PlayerHead {
    fun builder(textureUrl: String): ItemStackBuilder = ItemStack.builder(Material.PLAYER_HEAD)
        .meta(PlayerHeadMeta::class.java) {
            it.skullOwner(UUID.randomUUID())
                .playerSkin(PlayerSkin(encodeTexture(textureUrl), ""))
        }

    fun create(textureUrl: String): ItemStack = builder(textureUrl).build()

    private fun encodeTexture(url: String): String = Base64.getEncoder().encodeToString("{\"textures\":{\"SKIN\":{\"url\":\"$url\"}}}".encodeToByteArray())
}