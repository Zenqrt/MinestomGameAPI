package dev.zenqrt.game.api.event

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import dev.zenqrt.game.api.event.trait.GameEvent
import dev.zenqrt.game.api.event.trait.GamePlayerEvent
import net.minestom.server.event.EventFilter

object GameEventFilter {
    val GAME = EventFilter.from(GameEvent::class.java, Game::class.java) { it.game }
    val GAME_PLAYER = EventFilter.from(GamePlayerEvent::class.java, GamePlayer::class.java) { it.gamePlayer }
}