package dev.zenqrt.game.handler

import dev.zenqrt.game.Game
import dev.zenqrt.game.GameOptions
import dev.zenqrt.game.GamePlayer
import dev.zenqrt.game.event.GamePlayerJoinEvent
import dev.zenqrt.game.event.GamePlayerLeaveEvent
import net.minestom.server.MinecraftServer

class GamePlayerHandlerImpl(private val gameOptions: GameOptions) : GamePlayerHandler {

    override val players = mutableListOf<GamePlayer>()

    override fun join(gamePlayer: GamePlayer, game: Game): Boolean {
        if(players.size >= gameOptions.maxPlayers) return false
        val event = GamePlayerJoinEvent(game, gamePlayer)
        MinecraftServer.getGlobalEventHandler().call(event)
        if(event.isCancelled || !players.add(gamePlayer)) return false

        gamePlayer.currentGame = game
        return true
    }

    override fun leave(gamePlayer: GamePlayer, game: Game): Boolean {
        val event = GamePlayerLeaveEvent(game, gamePlayer)
        MinecraftServer.getGlobalEventHandler().call(event)
        if(event.isCancelled || !players.remove(gamePlayer)) return false

        gamePlayer.currentGame = null
        return true
    }
}