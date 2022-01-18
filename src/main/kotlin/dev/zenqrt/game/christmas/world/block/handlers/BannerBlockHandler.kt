package dev.zenqrt.game.christmas.world.block.handlers

import net.minestom.server.instance.block.BlockHandler
import net.minestom.server.tag.Tag
import net.minestom.server.utils.NamespaceID
import org.jglrxavpok.hephaistos.nbt.NBTCompound
import org.jglrxavpok.hephaistos.nbt.NBTList

class BannerBlockHandler : BlockHandler {
    override fun getBlockEntityTags(): MutableCollection<Tag<*>> = mutableListOf(
        Tag.NBT<NBTList<NBTCompound>>("Patterns")
    )

    override fun getBlockEntityAction(): Byte = 6
    override fun getNamespaceId(): NamespaceID = NamespaceID.from("minecraft:banner")
}