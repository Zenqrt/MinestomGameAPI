package dev.zenqrt.game.handler

import dev.zenqrt.game.Game
import dev.zenqrt.game.GamePlayer

interface GamePlayerHandler {
    val players: MutableList<GamePlayer>

    fun join(gamePlayer: GamePlayer, game: Game): Boolean
    fun leave(gamePlayer: GamePlayer, game: Game): Boolean
}