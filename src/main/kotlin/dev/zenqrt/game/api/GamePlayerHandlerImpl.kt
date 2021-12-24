package dev.zenqrt.game.api

import dev.zenqrt.game.api.event.GamePlayerJoinEvent
import dev.zenqrt.game.api.event.GamePlayerLeaveEvent
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import net.minestom.server.event.trait.CancellableEvent

class GamePlayerHandlerImpl : GamePlayerHandler {
    override val gamePlayers = mutableMapOf<Player, GamePlayer>()

    override fun insertPlayer(gamePlayer: GamePlayer, player: Player, game: Game): Boolean {
        if(validateCancellation(GamePlayerJoinEvent(game, gamePlayer, player))) return false

        gamePlayer.currentGame = game
        gamePlayers[player] = gamePlayer

        MinecraftServer.getGlobalEventHandler().call(GamePlayerPostJoinEvent(game, gamePlayer, player))
        return true
    }

    override fun removePlayer(gamePlayer: GamePlayer, player: Player, game: Game): Boolean {
        if(validateCancellation(GamePlayerLeaveEvent(game, gamePlayer, player))) return false

        gamePlayers.remove(player)
        gamePlayer.currentGame = null
        return true
    }

    private fun validateCancellation(cancellableEvent: CancellableEvent) : Boolean {
        MinecraftServer.getGlobalEventHandler().call(cancellableEvent)
        return cancellableEvent.isCancelled
    }

    override fun updatePlayer(gamePlayer: GamePlayer, player: Player): Boolean {
        return try {
            gamePlayers[player] = gamePlayer
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }
}