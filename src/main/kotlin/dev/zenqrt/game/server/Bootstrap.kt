package dev.zenqrt.game.server

import dev.zenqrt.game.api.MinestomGameAPI
import dev.zenqrt.game.christmas.registry.Registry
import net.minestom.server.MinecraftServer
import net.minestom.server.extras.MojangAuth
import net.minestom.server.extras.optifine.OptifineSupport

object MinestomServer {
    fun main(args: Array<String>) {
        val minecraftServer = MinecraftServer.init()

        MojangAuth.init()
        OptifineSupport.enable()
        MinestomGameAPI.init() // currently useless
        Registry.registerAll()

        minecraftServer.start("0.0.0.0", 25565)
    }
}