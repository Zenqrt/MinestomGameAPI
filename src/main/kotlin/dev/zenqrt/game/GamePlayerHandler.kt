package dev.zenqrt.game

import java.util.*

sealed interface GamePlayerHandler {
    val players: MutableMap<UUID, GamePlayer>

    fun insertPlayer(gamePlayer: GamePlayer, game: Game): Boolean
    fun removePlayer(gamePlayer: GamePlayer, game: Game): Boolean
}