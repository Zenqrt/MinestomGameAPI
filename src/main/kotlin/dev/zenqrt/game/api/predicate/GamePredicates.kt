package dev.zenqrt.game.api.predicate

import dev.zenqrt.game.api.Game
import dev.zenqrt.game.api.GamePlayer
import dev.zenqrt.game.api.event.trait.GamePlayerEvent
import net.minestom.server.entity.Player
import net.minestom.server.event.Event
import net.minestom.server.event.trait.PlayerEvent
import java.util.function.Predicate

open class GameOnlyPredicate<T : Event>(private val game: Game<out GamePlayer>, private val player: (T) -> Player) : Predicate<T> {
    override fun test(t: T): Boolean = game.gamePlayers.any { it.key == player }
}

class GamePlayerGameOnlyPredicate<T : GamePlayerEvent>(game: Game<out GamePlayer>) : GameOnlyPredicate<T>(game, { it.player })
class PlayerGameOnlyPredicate<T : PlayerEvent>(game: Game<out GamePlayer>) : GameOnlyPredicate<T>(game, { it.player })