package dev.zenqrt.game.event.trait

import dev.zenqrt.game.Game
import net.minestom.server.event.Event

interface GameEvent : Event {
    val game: Game
}