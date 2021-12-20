package dev.zenqrt.game

import dev.zenqrt.game.event.GamePlayerJoinEvent
import dev.zenqrt.game.event.GamePlayerLeaveEvent
import dev.zenqrt.game.event.GamePlayerPostJoinEvent
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import java.util.*

class GamePlayerHandlerImpl : GamePlayerHandler {

    override val gamePlayers = mutableMapOf<Player, GamePlayer>()

    override fun insertPlayer(gamePlayer: GamePlayer, player: Player, game: Game): Boolean {
        val globalEventHandler = MinecraftServer.getGlobalEventHandler()
        val event = GamePlayerJoinEvent(game, gamePlayer, player)

        globalEventHandler.call(event)
        if(event.isCancelled) return false

        gamePlayer.currentGame = game
        gamePlayers[player] = gamePlayer

        globalEventHandler.call(GamePlayerPostJoinEvent(game, gamePlayer, player))
        return true
    }

    override fun removePlayer(gamePlayer: GamePlayer, player: Player, game: Game): Boolean {
        val event = GamePlayerLeaveEvent(game, gamePlayer, player)
        MinecraftServer.getGlobalEventHandler().call(event)
        if(event.isCancelled) return false

        gamePlayers.remove(player)
        gamePlayer.currentGame = null
        return true
    }
}