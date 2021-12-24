package dev.zenqrt.game.api

import dev.zenqrt.game.api.event.GamePlayerJoinEvent
import dev.zenqrt.game.api.event.GamePlayerLeaveEvent
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.GamePlayerPostLeaveEvent
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.trait.CancellableEvent

class GamePlayerHandlerImpl : GamePlayerHandler {
    override val gamePlayers = mutableMapOf<Player, GamePlayer>()

    override fun insertPlayer(gamePlayer: GamePlayer, player: Player, game: Game): Boolean =
        handlePlayerOperation(GamePlayerJoinEvent(game, gamePlayer, player), GamePlayerPostJoinEvent(game, gamePlayer, player)) {
            gamePlayer.currentGame = game
            gamePlayers[player] = gamePlayer
        }

    override fun removePlayer(gamePlayer: GamePlayer, player: Player, game: Game): Boolean =
        handlePlayerOperation(GamePlayerLeaveEvent(game, gamePlayer, player), GamePlayerPostLeaveEvent(game, gamePlayer, player)) {
            gamePlayers.remove(player)
            gamePlayer.currentGame = null
        }

    private fun handlePlayerOperation(event: CancellableEvent, postEvent: Event, handler: () -> Unit): Boolean {
        if(validateCancellation(event)) return false
        handler.invoke()
        MinecraftServer.getGlobalEventHandler().call(postEvent)
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