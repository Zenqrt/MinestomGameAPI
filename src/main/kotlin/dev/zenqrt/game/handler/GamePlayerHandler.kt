package dev.zenqrt.game.handler

import dev.zenqrt.game.GamePlayer

interface GamePlayerHandler {
    val players: MutableList<GamePlayer>

    fun join(gamePlayer: GamePlayer): Boolean
    fun leave(gamePlayer: GamePlayer): Boolean
}