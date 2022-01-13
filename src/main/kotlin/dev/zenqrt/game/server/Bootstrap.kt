package dev.zenqrt.game.server

import dev.zenqrt.game.christmas.commands.GameCommand
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.registry.Registry
import dev.zenqrt.game.christmas.world.worlds.ChristmasMapWorld
import net.minestom.server.MinecraftServer
import net.minestom.server.extras.MojangAuth
import net.minestom.server.extras.optifine.OptifineSupport
import net.minestom.server.instance.InstanceContainer
import world.cepi.kstom.Manager

object MinestomServer {
    lateinit var instanceContainer: InstanceContainer
    lateinit var world: ChristmasMapWorld

    fun main(args: Array<String>) {
        val minecraftServer = MinecraftServer.init()

        MojangAuth.init()
        OptifineSupport.enable()
        Registry.registerAll()

        GameCommand.register()

        Manager.dimensionType.addDimension(ChristmasMapWorld.DIMENSION_TYPE)

        world = ChristmasMapWorld()
        instanceContainer = world.createInstanceContainer()

        minecraftServer.start("0.0.0.0", 25565)
    }
}