package dev.zenqrt.game.listener

import dev.zenqrt.game.GamePlayer

interface GamePlayerListener {
    fun onJoin(gamePlayer: GamePlayer)
    fun onLeave(gamePlayer: GamePlayer)
}