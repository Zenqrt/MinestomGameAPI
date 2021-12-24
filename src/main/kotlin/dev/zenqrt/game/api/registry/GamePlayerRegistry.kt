package dev.zenqrt.game.api.registry

import dev.zenqrt.game.api.GamePlayer
import java.util.*

class GamePlayerRegistry : Registry<UUID, GamePlayer> {
    private val players = mutableMapOf<UUID, GamePlayer>()

    override fun register(key: UUID, obj: GamePlayer) {
        players[key] = obj
    }

    override fun unregister(key: UUID, obj: GamePlayer) {
        players.remove(key)
    }

    override fun find(key: UUID): GamePlayer? = players[key]
}