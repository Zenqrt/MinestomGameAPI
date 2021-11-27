package game.phase

import dev.zenqrt.game.Game
import net.minestom.server.event.trait.PlayerEvent
import java.util.function.Predicate

class GameOnlyPredicate<T : PlayerEvent>(private val game: Game) : Predicate<T> {
    override fun test(t: T): Boolean = game.players.any { it == t.player }
}