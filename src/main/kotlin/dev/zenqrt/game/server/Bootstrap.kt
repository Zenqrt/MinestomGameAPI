package dev.zenqrt.game.server

import dev.zenqrt.game.api.MinestomGameAPI
import dev.zenqrt.game.christmas.commands.GameCommand
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.registry.Registry
import dev.zenqrt.game.christmas.world.worlds.ChristmasMapWorld
import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.extras.MojangAuth
import net.minestom.server.extras.optifine.OptifineSupport
import net.minestom.server.instance.InstanceContainer
import world.cepi.kstom.Manager
import world.cepi.kstom.event.listenOnly

object MinestomServer {
    lateinit var instanceContainer: InstanceContainer

    fun main(args: Array<String>) {
        val minecraftServer = MinecraftServer.init()

        MojangAuth.init()
        OptifineSupport.enable()
        MinestomGameAPI.init() // currently useless
        Registry.registerAll()

        val world = ChristmasMapWorld()
        instanceContainer = world.createInstanceContainer()

        Manager.globalEvent.listenOnly<PlayerLoginEvent> {
            this.setSpawningInstance(instanceContainer)
            this.player.respawnPoint = world.spawnPos
        }

        GameCommand.register()

        minecraftServer.start("0.0.0.0", 25565)
    }
}