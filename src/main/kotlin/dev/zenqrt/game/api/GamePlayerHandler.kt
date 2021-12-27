package dev.zenqrt.game.api

import net.minestom.server.entity.Player

interface GamePlayerHandler<T : GamePlayer> {
    val gamePlayers: MutableMap<Player, T>

    fun insertPlayer(gamePlayer: T, player: Player, game: Game<T>): Boolean
    fun removePlayer(gamePlayer: T, player: Player, game: Game<T>): Boolean
    fun updatePlayer(gamePlayer: T, player: Player): Boolean
    fun createPlayer(player: Player): T
}