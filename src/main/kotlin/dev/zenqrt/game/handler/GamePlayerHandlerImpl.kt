package dev.zenqrt.game.handler

import dev.zenqrt.game.GameOptions
import dev.zenqrt.game.GamePlayer
import dev.zenqrt.game.listener.GamePlayerListener
import dev.zenqrt.game.listener.GamePlayerListenerImpl

class GamePlayerHandlerImpl(private val gameOptions: GameOptions,
                            private val gamePlayerListener: GamePlayerListener = GamePlayerListenerImpl()) : GamePlayerHandler {

    override val players = mutableListOf<GamePlayer>()

    override fun join(gamePlayer: GamePlayer): Boolean {
        if(players.size >= gameOptions.maxPlayers) return false
        if(!players.add(gamePlayer)) return false
        gamePlayerListener.onJoin(gamePlayer)
        return true
    }

    override fun leave(gamePlayer: GamePlayer): Boolean {
        if(!players.remove(gamePlayer)) return false
        gamePlayerListener.onLeave(gamePlayer)
        return true
    }
}