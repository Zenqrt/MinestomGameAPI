package dev.zenqrt.game.christmas.world.worlds

import dev.zenqrt.game.christmas.world.MinecraftWorld
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.AnvilLoader
import net.minestom.server.instance.IChunkLoader
import net.minestom.server.utils.NamespaceID
import net.minestom.server.world.DimensionType

class ChristmasMapWorld : MinecraftWorld {
    override val spawnPos: Pos = Pos(-22.5, 65.0, 2.5)
    override val dimensionType: DimensionType = DIMENSION_TYPE
    override val chunkLoader: IChunkLoader = AnvilLoader("./src/main/resources/worlds/christmas_map")

    companion object {
        val DIMENSION_TYPE: DimensionType = DimensionType.builder(NamespaceID.from("christmas_map"))
            .skylightEnabled(true)
            .build()
    }
}