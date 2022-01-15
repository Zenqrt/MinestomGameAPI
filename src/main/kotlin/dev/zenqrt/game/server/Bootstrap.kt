package dev.zenqrt.game.server

import dev.zenqrt.game.christmas.commands.GameCommand
import dev.zenqrt.game.christmas.registry.Registry
import dev.zenqrt.game.christmas.world.worlds.ChristmasMapWorld
import dev.zenqrt.game.christmas.world.worlds.HalloweenLobbyWorld
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.GameMode
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.extras.MojangAuth
import net.minestom.server.extras.optifine.OptifineSupport
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.utils.NamespaceID
import net.minestom.server.world.biomes.Biome
import world.cepi.kstom.Manager
import world.cepi.kstom.event.listenOnly

object MinestomServer {
    lateinit var instanceContainer: InstanceContainer
    lateinit var world: ChristmasMapWorld

    @JvmStatic
    fun main(args: Array<String>) {
        val minecraftServer = MinecraftServer.init()

        registerAll()

        world = ChristmasMapWorld()
        instanceContainer = world.createInstanceContainer()

        Manager.globalEvent.listenOnly<PlayerLoginEvent> {
            this.setSpawningInstance(instanceContainer)
            this.player.respawnPoint = world.spawnPos
            this.player.gameMode = GameMode.CREATIVE
            this.player.isAllowFlying = true
            this.player.isFlying = true
        }

        minecraftServer.start("0.0.0.0", 25565)
    }

    private fun registerAll() {
        MojangAuth.init()
        OptifineSupport.enable()
        Registry.registerAll()

        GameCommand.register()
        registerWorlds()
    }

    fun registerWorlds() {
        Manager.dimensionType.addDimension(ChristmasMapWorld.DIMENSION_TYPE)
        Manager.biome.addBiome(Biome.builder()
            .name(NamespaceID.from("snowy_plains"))
            .temperature(0F)
            .build())
    }
}