package dev.zenqrt.game.christmas.world

import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.IChunkLoader
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.tag.Tag
import net.minestom.server.world.DimensionType
import world.cepi.kstom.Manager

interface MinecraftWorld {
    val spawnPos: Pos
    val dimensionType: DimensionType
    val chunkLoader: IChunkLoader

    fun createInstanceContainer(): InstanceContainer = Manager.instance.createInstanceContainer(dimensionType).also {
        it.chunkLoader = chunkLoader
        it.setTag(Tag.Structure("SpawnPosition", PosTagSerializer()), spawnPos)
    }
}