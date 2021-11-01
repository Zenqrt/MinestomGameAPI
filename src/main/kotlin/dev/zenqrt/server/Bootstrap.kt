package dev.zenqrt.server

import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.event.player.PlayerLoginEvent

fun main() {
    val server = MinecraftServer.init()
    server.start("0.0.0.0", 25565)

    val instanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer()

    val eventManager = MinecraftServer.getGlobalEventHandler();
    eventManager.addListener(PlayerLoginEvent::class.java) {
        val player = it.player
        player.respawnPoint = Pos(0.0,40.0,0.0)
        it.setSpawningInstance(instanceContainer)
    }
}