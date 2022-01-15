package dev.zenqrt.game.christmas.world.generator

import net.minestom.server.instance.ChunkGenerator
import net.minestom.server.instance.ChunkPopulator
import net.minestom.server.instance.batch.ChunkBatch

class EmptyChunkGenerator : ChunkGenerator {

    override fun generateChunkData(batch: ChunkBatch, chunkX: Int, chunkZ: Int) {

    }

    override fun getPopulators(): MutableList<ChunkPopulator>? = null
}