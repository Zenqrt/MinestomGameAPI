package dev.zenqrt.game

import dev.zenqrt.game.event.GamePlayerJoinEvent
import dev.zenqrt.game.event.GamePlayerLeaveEvent
import net.minestom.server.MinecraftServer
import java.util.*

class GamePlayerHandlerImpl : GamePlayerHandler {

    override val players = mutableMapOf<UUID, GamePlayer>()

    override fun insertPlayer(gamePlayer: GamePlayer, game: Game): Boolean {
        val event = GamePlayerJoinEvent(game, gamePlayer)
        MinecraftServer.getGlobalEventHandler().call(event)
        if(event.isCancelled) return false

        gamePlayer.currentGame = game
        players[gamePlayer.player.uuid] = gamePlayer
        return true
    }

    override fun removePlayer(gamePlayer: GamePlayer, game: Game): Boolean {
        val event = GamePlayerLeaveEvent(game, gamePlayer)
        MinecraftServer.getGlobalEventHandler().call(event)
        if(event.isCancelled) return false

        players.remove(gamePlayer.player.uuid)
        gamePlayer.currentGame = null
        return true
    }
}