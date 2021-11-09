package dev.zenqrt.game.handler

import dev.zenqrt.game.GamePlayer

interface GamePlayerHandler {
    fun join(gamePlayer: GamePlayer)
    fun leave(gamePlayer: GamePlayer)
}