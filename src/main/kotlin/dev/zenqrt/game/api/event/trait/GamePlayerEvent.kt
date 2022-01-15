package dev.zenqrt.game.api.event.trait

import dev.zenqrt.game.api.GamePlayer
import net.minestom.server.event.trait.PlayerEvent

interface GamePlayerEvent : PlayerEvent, GameEvent {
    val gamePlayer: GamePlayer
}