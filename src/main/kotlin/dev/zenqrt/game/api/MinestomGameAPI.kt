package dev.zenqrt.game.api

import dev.zenqrt.game.api.extensions.createGamePlayer
import dev.zenqrt.game.api.registry.GamePlayerRegistry
import dev.zenqrt.game.api.registry.GameRegistry
import net.minestom.server.MinecraftServer
import net.minestom.server.event.Event
import net.minestom.server.event.EventListener
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.PlayerLoginEvent

object MinestomGameAPI {
    val playerRegistry = GamePlayerRegistry()
    val gameRegistry = GameRegistry()

    fun init() {
        registerEvents(MinecraftServer.getGlobalEventHandler())
    }

    private fun registerEvents(eventNode: EventNode<Event>) {
        eventNode.addListener(EventListener.of(PlayerLoginEvent::class.java) {
            val player = it.player
            if(playerRegistry.find(player.uuid) != null) return@of
            player.createGamePlayer(playerRegistry)
        })
    }
}