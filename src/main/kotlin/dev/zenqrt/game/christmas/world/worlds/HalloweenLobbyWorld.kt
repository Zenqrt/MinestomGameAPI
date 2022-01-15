package dev.zenqrt.game.christmas.world.worlds

import dev.zenqrt.game.christmas.world.MinecraftWorld
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.AnvilLoader
import net.minestom.server.instance.IChunkLoader
import net.minestom.server.world.DimensionType

class HalloweenLobbyWorld : MinecraftWorld {
    override val spawnPos: Pos = Pos.ZERO.withY(68.0)
    override val dimensionType: DimensionType = ChristmasMapWorld.DIMENSION_TYPE
    override val chunkLoader: IChunkLoader = AnvilLoader(getWorldPath("halloween_lobby")!!)
}