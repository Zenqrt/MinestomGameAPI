package dev.zenqrt.game.extensions

import dev.zenqrt.game.GamePlayer
import dev.zenqrt.game.registry.GamePlayerRegistry
import net.minestom.server.entity.Player

fun Player.createGamePlayer(registry: GamePlayerRegistry): GamePlayer {
    val gamePlayer = GamePlayer(this.uuid)
    registry.register(this.uuid, gamePlayer)
    return gamePlayer
}