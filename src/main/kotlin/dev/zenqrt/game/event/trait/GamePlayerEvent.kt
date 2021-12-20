package dev.zenqrt.game.event.trait

import dev.zenqrt.game.GamePlayer
import net.minestom.server.event.Event
import net.minestom.server.event.trait.PlayerEvent

interface GamePlayerEvent : PlayerEvent {
    val gamePlayer: GamePlayer
}