package dev.zenqrt.game.event.trait

import dev.zenqrt.game.GamePlayer
import net.minestom.server.event.Event

interface GamePlayerEvent : Event {
    val gamePlayer: GamePlayer
}