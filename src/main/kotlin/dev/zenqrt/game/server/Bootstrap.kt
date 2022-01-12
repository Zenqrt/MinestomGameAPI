package dev.zenqrt.game.server

import dev.zenqrt.game.christmas.commands.GameCommand
import dev.zenqrt.game.christmas.game.ChristmasGame
import dev.zenqrt.game.christmas.registry.Registry
import net.minestom.server.MinecraftServer
import net.minestom.server.extras.MojangAuth
import net.minestom.server.extras.optifine.OptifineSupport

object MinestomServer {
    lateinit var mainGame: ChristmasGame

    fun main(args: Array<String>) {
        val minecraftServer = MinecraftServer.init()

        MojangAuth.init()
        OptifineSupport.enable()
        Registry.registerAll()

        GameCommand.register()

        minecraftServer.start("0.0.0.0", 25565)
    }
}