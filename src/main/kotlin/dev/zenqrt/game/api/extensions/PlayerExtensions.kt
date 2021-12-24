package dev.zenqrt.game.api.extensions

import dev.zenqrt.game.api.GamePlayer
import dev.zenqrt.game.api.registry.GamePlayerRegistry
import net.minestom.server.entity.Player

fun Player.createGamePlayer(registry: GamePlayerRegistry): GamePlayer {
    val gamePlayer = GamePlayer(this.uuid)
    registry.register(this.uuid, gamePlayer)
    return gamePlayer
}