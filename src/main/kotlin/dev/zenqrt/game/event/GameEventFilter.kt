package dev.zenqrt.game.event

import dev.zenqrt.game.Game
import dev.zenqrt.game.GamePlayer
import dev.zenqrt.game.event.trait.GameEvent
import dev.zenqrt.game.event.trait.GamePlayerEvent
import net.minestom.server.event.EventFilter

object GameEventFilter {
    val GAME = EventFilter.from(GameEvent::class.java, Game::class.java) { it.game }
    val GAME_PLAYER = EventFilter.from(GamePlayerEvent::class.java, GamePlayer::class.java) { it.gamePlayer }
}