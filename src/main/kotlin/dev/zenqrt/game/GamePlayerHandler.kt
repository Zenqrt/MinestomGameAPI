package dev.zenqrt.game

import net.minestom.server.entity.Player
import java.util.*

sealed interface GamePlayerHandler {
    val gamePlayers: MutableMap<Player, GamePlayer>

    fun insertPlayer(gamePlayer: GamePlayer, player: Player, game: Game): Boolean
    fun removePlayer(gamePlayer: GamePlayer, player: Player, game: Game): Boolean
}