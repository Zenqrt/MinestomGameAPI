package dev.zenqrt.game.christmas.world.block.handlers

import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.tag.Tag
import net.minestom.server.utils.NamespaceID
import org.jglrxavpok.hephaistos.nbt.NBTCompound

class SkullBlockHandler : BlockHandler {
    override fun getBlockEntityTags(): MutableCollection<Tag<*>> = mutableListOf(
        Tag.String("ExtraType"),
        Tag.NBT<NBTCompound>("SkullOwner")
    )

    override fun getBlockEntityAction(): Byte = 4
    override fun getNamespaceId(): NamespaceID = NamespaceID.from("minecraft:skull")
}