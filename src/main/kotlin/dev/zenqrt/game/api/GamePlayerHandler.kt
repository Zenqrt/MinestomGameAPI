package dev.zenqrt.game.api

import net.minestom.server.entity.Player

interface GamePlayerHandler {
    val gamePlayers: MutableMap<Player, GamePlayer>

    fun insertPlayer(gamePlayer: GamePlayer, player: Player, game: Game): Boolean
    fun removePlayer(gamePlayer: GamePlayer, player: Player, game: Game): Boolean
    fun updatePlayer(gamePlayer: GamePlayer, player: Player): Boolean
}