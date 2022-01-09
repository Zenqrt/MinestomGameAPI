package dev.zenqrt.game.christmas.item.toy

import dev.zenqrt.game.christmas.item.Item
import dev.zenqrt.game.christmas.utils.PlayerHead
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration

class WrappedPresentItem(presentName: String, wrappingColor: WrappingColor) : Item {
    override val id = "wrapped_present"
    override val model = PlayerHead.builder(wrappingColor.textureUrl)
        .displayName(
            Component.text("Wrapped Present", wrappingColor.textColor)
                .decoration(TextDecoration.ITALIC, false)
        ).lore(
            Component.empty(),
            Component.text(presentName, NamedTextColor.GRAY)
        ).build()
}

interface WrappingColor {
    val textColor: TextColor
    val textureUrl: String
}

enum class WrappingColors(override val textColor: TextColor, override val textureUrl: String) : WrappingColor {
    RED(NamedTextColor.RED, "http://textures.minecraft.net/texture/6cef9aa14e884773eac134a4ee8972063f466de678363cf7b1a21a85b7"),
    GREEN(NamedTextColor.GREEN, "http://textures.minecraft.net/texture/d08ce7deba56b726a832b61115ca163361359c30434f7d5e3c3faa6fe4052"),
    PURPLE(NamedTextColor.LIGHT_PURPLE, "http://textures.minecraft.net/texture/1b6730de7e5b941efc6e8cbaf5755f9421a20de871759682cd888cc4a81282"),
    CYAN(NamedTextColor.AQUA, "http://textures.minecraft.net/texture/7fcd1c82e2fb3fa368cfa9a506ab6c98647595d215d6471ad47cce29685af")
}