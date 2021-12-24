package dev.zenqrt.game.api.event.trait

import dev.zenqrt.game.api.Game
import net.minestom.server.event.Event

interface GameEvent : Event {
    val game: Game
}