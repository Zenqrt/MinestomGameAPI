package dev.zenqrt.game.api

import dev.zenqrt.game.api.event.GamePlayerJoinEvent
import dev.zenqrt.game.api.event.GamePlayerLeaveEvent
import dev.zenqrt.game.api.event.GamePlayerPostJoinEvent
import dev.zenqrt.game.api.event.GamePlayerPostLeaveEvent
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.trait.CancellableEvent

abstract class AbstractGamePlayerHandler<T : GamePlayer> : GamePlayerHandler<T> {
    override val gamePlayers = mutableMapOf<Player, T>()

    override fun insertPlayer(gamePlayer: T, player: Player, game: Game<T>): Boolean =
        handlePlayerOperation(GamePlayerJoinEvent(game, gamePlayer, player), GamePlayerPostJoinEvent(game, gamePlayer, player)) {
            game.players.add(player)
            gamePlayer.currentGame = game
            gamePlayers[player] = gamePlayer
        }

    override fun removePlayer(gamePlayer: T, player: Player, game: Game<T>): Boolean =
        handlePlayerOperation(GamePlayerLeaveEvent(game, gamePlayer, player), GamePlayerPostLeaveEvent(game, gamePlayer, player)) {
            game.players.remove(player)
            gamePlayers.remove(player)
            gamePlayer.currentGame = null
        }

    private fun handlePlayerOperation(event: CancellableEvent, postEvent: Event, handler: () -> Unit): Boolean = this.let {
        if(validateCancellation(event)) return false
        handler.invoke()
        MinecraftServer.getGlobalEventHandler().call(postEvent)
        true
    }

    private fun validateCancellation(cancellableEvent: CancellableEvent) : Boolean {
        MinecraftServer.getGlobalEventHandler().call(cancellableEvent)
        return cancellableEvent.isCancelled
    }

    override fun updatePlayer(gamePlayer: T, player: Player): Boolean {
        return try {
            gamePlayers[player] = gamePlayer
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }
}