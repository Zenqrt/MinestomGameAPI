package dev.zenqrt.game.api.event.trait

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import net.minestom.server.event.Event

interface GameEvent : Event {
    val game: Game<out GamePlayer>
}