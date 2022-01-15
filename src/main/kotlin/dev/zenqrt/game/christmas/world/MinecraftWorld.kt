package dev.zenqrt.game.christmas.world

import com.sun.jdi.Bootstrap
import dev.zenqrt.game.christmas.world.generator.EmptyChunkGenerator
import net.minestom.server.coordinate.Pos
import net.minestom.server.instance.IChunkLoader
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.tag.Tag
import net.minestom.server.world.DimensionType
import world.cepi.kstom.Manager
import java.net.URISyntaxException
import java.nio.file.Path

interface MinecraftWorld {
    val spawnPos: Pos
    val dimensionType: DimensionType
    val chunkLoader: IChunkLoader

    fun createInstanceContainer(): InstanceContainer = Manager.instance.createInstanceContainer(dimensionType).also {
        it.chunkLoader = chunkLoader
        it.chunkGenerator = EmptyChunkGenerator()
        it.setTag(Tag.Structure("SpawnPosition", PosTagSerializer()), spawnPos)
    }

    fun getWorldPath(worldName: String): Path? {
        val url = Bootstrap::class.java.classLoader.getResource("worlds/$worldName") ?: throw IllegalArgumentException("World folder not found!")

        return try {
            Path.of(url.toURI())
        } catch (ex: URISyntaxException) {
            ex.printStackTrace()
            return null
        }
    }
}